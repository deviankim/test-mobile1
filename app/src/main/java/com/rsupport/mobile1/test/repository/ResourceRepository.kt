package com.rsupport.mobile1.test.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    private val resources = context.resources

    fun getDimensionResource(id: Int): Float {
        return resources.getDimension(id)
    }
}