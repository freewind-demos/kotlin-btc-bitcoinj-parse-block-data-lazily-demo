package example

import org.bitcoinj.core.Context
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.utils.BlockFileLoader
import java.io.File
import kotlin.sequences.asSequence

val mainNetParams = MainNetParams()
val blockChainFiles = listOf(File("./btc-data/blocks/blk00000.dat"))

fun main(args: Array<String>) {
    Context.getOrCreate(mainNetParams)

    loading()
    lazyLoading()

}

private fun loading() {
    println("----------- loading ------------")
    val loader = BlockFileLoader(mainNetParams, blockChainFiles)
    loader.mapIndexed { index, block ->
        System.out.println("---- $index ------")
        index to block.hash
    }.take(10).forEach { (index, hash) ->
        println("$index: $hash")
    }
}

private fun lazyLoading() {
    println("----------- lazyLoading ------------")
    val loader = BlockFileLoader(mainNetParams, blockChainFiles)
    loader.asSequence()
            .mapIndexed { index, block ->
                System.out.println("---- $index ------")
                index to block.hash
            }.take(10)
            .forEach { (index, hash) ->
                println("$index: $hash")
            }
}