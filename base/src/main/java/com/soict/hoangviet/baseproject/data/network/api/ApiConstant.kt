package com.soict.hoangviet.baseproject.data.network

import okhttp3.logging.HttpLoggingInterceptor
import java.util.logging.Level

object ApiConstant {
    interface RelativeUrl {
        companion object {
        }
    }

    interface RequestParam {
        companion object {
            const val AUTHORIZATION_HEADER = "Authorization"
            const val BEARER = "Bearer "
            const val CATEGORY_ID = "cat_id"
            const val CATE_ID = "category_id"
            const val PAGE = "page"
            const val LIMIT = "limit"
            const val ID = "id"
            const val REFERENCE_ID = "reference_id"
            const val KEYWORD = "s"
            const val CAT_ID = "cat_id" //create constant by Phạm Công Hoan 10/19/2018
            const val ACCESS_TOKEN_INSTAGRAM = "access_token"
            const val NAME = "name"
            const val CONTENT = "content"
            const val LAT = "lat"
            const val LNG = "lng"
            const val CAT_IDS = "cat_ids"
            const val POINT_TAGS = "point_tags"
            const val AVATAR = "avatar"
            const val VERSION = "app_version"
            const val METHOD = "_method"
            const val USER_ID = "user_id"
            const val DEVICE_ID = "device_id"
        }
    }

    interface Method {
        companion object {
            const val PUT = "PUT"
            const val DELETE = "DELETE"
        }
    }

    interface HttpStatusCode {
        companion object {
            const val UNKNOWN = -1
            const val OK = 200
            const val CREATED = 201
            const val NONE = 204
            const val BAD_REQUEST = 400
            const val UNAUTHORIZED = 401
            const val FORBIDDEN = 403
            const val NOT_FOUND = 404
            const val PRECONDITION_FAILED = 412
            const val UNPROCESSABLE = 422
            const val CART_CHECK_OUT = 473
        }
    }

    interface Timeout {
        companion object {
            const val CONNECT = 5L
            const val READ = 5L
            const val WRITE = 5L
            const val CALL = 10L
        }
    }

    interface LoggingLevel {
        companion object {
            val NONE = HttpLoggingInterceptor.Level.NONE
            val BASIC = HttpLoggingInterceptor.Level.BASIC
            val HEADERS = HttpLoggingInterceptor.Level.HEADERS
            val BODY = HttpLoggingInterceptor.Level.BODY
        }
    }

    interface HttpMessage {
        companion object {
            const val ERROR_TRY_AGAIN = "Có lỗi xảy ra. Vui lòng thử lại"
            const val TIME_OUT = "Thời gian kết nối quá lâu. Vui lòng thử lại"
        }
    }

    interface ErrorCode {
        companion object {
            const val INVALID_TOKEN = -10002
        }
    }
}