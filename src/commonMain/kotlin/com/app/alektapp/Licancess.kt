package com.app.alektapp

enum class Documents{
    TERMS_OF_USAGE,
    PRIVACY_POLICY;

    val url: String
        get() = when (this) {
            PRIVACY_POLICY -> "https://3l5qj7k4c4v6cl5mmsusqeiseq0wbzck.lambda-url.eu-central-1.on.aws/"
            TERMS_OF_USAGE -> "https://ggfxp2hsfm2osvbm2fv37vopea0jegxd.lambda-url.eu-central-1.on.aws/"
        }


    val title: String
        get() = when (this) {
            PRIVACY_POLICY -> "Privacy Policy"
            TERMS_OF_USAGE -> "Terms Of Usage"
        }

}