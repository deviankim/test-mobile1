package com.rsupport.mobile1.test.ui.activity

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.ui.adapter.ImageAdapter
import com.rsupport.mobile1.test.ui.dialog.DismissDialog
import com.rsupport.mobile1.test.ui.dialog.ConfirmDialog
import com.rsupport.mobile1.test.ui.viewmodel.MainViewModel
import com.rsupport.mobile1.test.utils.NetworkUtils.isNetworkAvailable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check network availability before proceeding
        if (!isNetworkAvailable(this)) {
            showNoNetworkDialog()
            return
        }

        // Set up view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the image adapter and RecyclerView
        imageAdapter = ImageAdapter(this)
        setupRecyclerView()

        // Observe ViewModel data and update UI accordingly
        observeViewModel()

        // Handle back button press to show a confirmation dialog
        setupOnBackPressed()
    }

    // Set up the RecyclerView with a GridLayoutManager and scroll listener
    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0 && binding.fabScrollTop.visibility == View.GONE) {
                        binding.fabScrollTop.show()
                    } else if (dy <= 0 && binding.fabScrollTop.visibility == View.VISIBLE) {
                        binding.fabScrollTop.hide()
                    }
                }
            })
        }

        // Scroll to top when the button is clicked
        binding.fabScrollTop.setOnClickListener { binding.recyclerView.smoothScrollToPosition(0) }
    }

    // Observe LiveData from ViewModel and update the UI
    private fun observeViewModel() {
        viewModel.images.observe(this) { images ->
            imageAdapter.submitList(images)
        }

        viewModel.loading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }
    }

    // Handle the back button press to show a confirmation dialog
    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                ConfirmDialog(
                    this@MainActivity,
                    title = getString(R.string.exit_title),
                    message = getString(R.string.exit_content),
                    onConfirm = { finish() }
                ).show()
            }
        })
    }

    // Inflate the options menu with OSS licenses item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Handle options menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionOssLicenses -> {
                // Set the title for the Open Source Software licenses screen
                OssLicensesMenuActivity.setActivityTitle("Open Source Licenses")
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Show or hide the loading view based on the loading state
    private fun showLoading(show: Boolean) {
        if (show) {
            showLoadingView()
        } else {
            hideLoadingView()
        }
    }

    // Show the loading animation
    private fun showLoadingView() {
        binding.loadingImageView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE

        val animation = binding.loadingImageView.drawable as? AnimationDrawable
        animation?.start()
    }

    // Hide the loading animation
    private fun hideLoadingView() {
        val animation = binding.loadingImageView.drawable as? AnimationDrawable
        animation?.stop()

        binding.loadingImageView.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    // Show a dialog when no network is available
    private fun showNoNetworkDialog() {
        DismissDialog(
            this@MainActivity,
            title = getString(R.string.network_error_title),
            message = getString(R.string.network_error_content),
            onDismiss = {finish()}

        ).show()
    }
}
