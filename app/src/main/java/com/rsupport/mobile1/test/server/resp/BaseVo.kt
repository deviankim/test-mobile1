package com.rsupport.mobile1.test.server.resp

import com.google.gson.JsonElement
import java.io.Serializable


open class BaseVo : Serializable {

    var result: String? = null      // Request 처리 결과 코드
    var msg: String? = null         // Request 처리 결과 메시지
    var data: JsonElement? = null
    var error:String? = null
    var code:Int? = null

    /*
        public EncryptedMessage getEncryptData() {
            return data;
        }

        public void setEncryptData(EncryptedMessage data) {
            this.data = data;
        }
        */
    var resultYn: Boolean = false
        private set
    private var RESULT_CATEGORY: String? = null
    val resultCategory: Int = 0
    private var RESULT_CODE: String? = null
    var resultCode: Int = 0
        private set

    override fun toString(): String {
        return "BaseVo{ " +
                "result='" + result + '\''.toString() +
                ", msg='" + msg + '\''.toString() +
                ", data='" + data +
                '}'.toString()
    }



}
