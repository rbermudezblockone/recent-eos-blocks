package one.block.recenteosblocks.data.network

import one.block.recenteosblocks.data.db.entities.Block
import one.block.recenteosblocks.data.db.entities.BlockchainInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EosAPI {

    @GET("get_info")
    suspend fun getBlockchainInfo() : Response<BlockchainInfo>

    @FormUrlEncoded
    @POST("get_block")
    fun getBlockDetail(
        @Field("block_num_or_id") blockNumber: Int
    ) : Response<Block>

    companion object {
        operator fun invoke() : EosAPI {
            return Retrofit.Builder()
                .baseUrl("https://eos.greymass.com/v1/chain/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EosAPI::class.java)
        }
    }
}