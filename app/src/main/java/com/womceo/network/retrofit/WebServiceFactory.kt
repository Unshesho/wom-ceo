package com.womceo.network.retrofit

class WebServiceFactory<TWebService>(
    private val tClass: Class<TWebService>,
) {
    fun createRemoteWebServiceConfig(): TWebService {
        return RemoteWebService<TWebService>().create(
            tClass = tClass
        )
    }
}
