package one.block.recenteosblocks.data.db.entities

data class Block(
    var id: String? = null,
    var blockNum: String? = null,
    var timestamp: String? = null,
    var producer: String? = null,
    var confirmed: String? = null,
    var previous: String? = null,
    var transactionMroot: String? = null,
    var actionMroot: String? = null,
    var scheduleVersion: String? = null,
    var refBlockPrefix: String? = null
)