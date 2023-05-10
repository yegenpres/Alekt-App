package com.app.alektapp.domain.log

import io.github.aakira.napier.log

open class EventLogger(val tag: String) {
   fun printLog() {
        log(tag = tag) {
            this.toString()
        }
    }
}

class Log {
    companion object {
        fun native(message: String, tag: String = "",) {
            log(tag = "native$tag") { message }
        }
    }
}