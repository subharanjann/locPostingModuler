package com.mobillor.locpostingmodule.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.mobillor.locpostingmodule.domain.useCase.GetBinLocationUseCase
import com.mobillor.locpostingmodule.domain.useCase.GetLocationUseCase
import com.mobillor.locpostingmodule.domain.useCase.GetPalletLocationUseCase
import com.mobillor.locpostingmodule.domain.useCase.PutawayItemUseCase
import com.mobillor.locpostingmodule.data.model.InputMappedtoItem

import com.mobillor.locpostingmodule.data.model.ResponseBinCode
import com.mobillor.locpostingmodule.data.model.ResponseLocationCode
import com.mobillor.locpostingmodule.data.model.ResponsePalletCode
import com.mobillor.locpostingmodule.data.model.ResponsePutawayItemTransfer
import com.mobillor.locpostingmodule.util.Resource
import kotlinx.coroutines.launch

class ItemPutawayCompletionVm(
    private val getLocationUseCase: GetLocationUseCase,
    private val getPalletLocationUseCase: GetPalletLocationUseCase,
    private val getBinLocationUseCase: GetBinLocationUseCase,
    private val putawayItemUseCase: PutawayItemUseCase
):ViewModel() {
    private val  _locationDataResponseLiveData = MutableLiveData<Resource<ResponseLocationCode>>()
    val locationDataResponseLiveData: LiveData<Resource<ResponseLocationCode>> get() = _locationDataResponseLiveData

    private val _palletCodeResponseLiveData = MutableLiveData<Resource<ResponsePalletCode>>()
    val palletCodeResponseLiveData : LiveData<Resource<ResponsePalletCode>> get() = _palletCodeResponseLiveData

    private val _binCodeResponseLiveData = MutableLiveData<Resource<ResponseBinCode>>()
    val binCodeResponseLiveData : LiveData<Resource<ResponseBinCode>>get() = _binCodeResponseLiveData

    private val _itemPutawayResponseLiveData = MutableLiveData<Resource<ResponsePutawayItemTransfer>>()
    val itemPutawayResponseLiveData : LiveData<Resource<ResponsePutawayItemTransfer>>get()= _itemPutawayResponseLiveData



        fun getLocationCode(token :String, scannedloc : String){
       viewModelScope.launch {
           val result = getLocationUseCase(token , scannedloc)
           _locationDataResponseLiveData.postValue(result)
       }
    }

    fun  getPalletCode(token :String,scannedPallet: String){
        viewModelScope.launch {
            val result = getPalletLocationUseCase(token,scannedPallet)
            _palletCodeResponseLiveData.postValue(result)

        }
    }

    fun getBinCode(token :String,scannedBin :String){
       viewModelScope.launch {
           val result = getBinLocationUseCase(token, scannedBin)
           _binCodeResponseLiveData.postValue(result)
       }
    }

    fun itemPutaway(token :String,dataSet : InputMappedtoItem){
        viewModelScope.launch {
            val result = putawayItemUseCase(token,dataSet)
            _itemPutawayResponseLiveData.postValue(result)
        }
    }
}


//
//(application: Application) : AndroidViewModel(application) {
//    val repository :  ItemPutawayRepository = ItemPutawayRepository(application)
//
 //   val locationDataResponseLiveData: LiveData<ResponseLocationCode> get() = repository.locationDataResponseLiveData
//    val palletCodeResponseLiveData : LiveData<ResponsePalletCode> get() = repository.palletCodeResponseLiveData
//
//    val binCodeResponseLiveData : LiveData<ResponseBinCode>get() = repository.binCodeResponseLiveData
//
//    val itemPutawayResponseLiveData : LiveData<ResponsePutawayItemTransfer>get()= repository.itemTransferResponseLiveData
//
//
//
//    fun getLocationCode(scannedloc : String){
//        repository.fetchLocationCode(scannedloc)
//    }
//
//    fun  getPalletCode(scannedPallet: String){
//        repository.fetchPalletCode(scannedPallet)
//    }
//
//    fun getBinCode(scannedBin :String){
//        repository.fetchBinCode(scannedBin)
//    }
//
//    fun itemPutaway(dataSet : InputMappedtoItem){
//        repository.fetchResponseFromItemTransfer(dataSet)
//    }
//}