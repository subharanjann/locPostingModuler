package com.mobillor.locpostingmodule.data.model

data class ResponsePutawayPalletTransfer(
    val `data`: Data,
    val msg: String,
    val status: Boolean,
    val statusCode: Int
) {
    data class Data(
        val asnCode: String?,
        val batchNumber: String?,
        val grnLineNumber: String?,
        val grnNumber: String?,
        val itemCode: String?,
        val itemDescription: String?,
        val itemId: Int?,
        val locationCode: String?,
        val locationId: Int?,
        val palletId: String?,
        val qty: Double?,
        val sku: String?,
        val suid: String?,
        val uom: String?
    )
}