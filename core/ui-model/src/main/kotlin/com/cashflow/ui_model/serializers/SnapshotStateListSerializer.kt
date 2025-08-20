package com.cashflow.ui_model.serializers

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class SnapshotStateListSerializer<T>(private val itemSerializer: KSerializer<T>) : KSerializer<SnapshotStateList<T>> {
    override val descriptor: SerialDescriptor = itemSerializer.descriptor
    override fun deserialize(decoder: Decoder): SnapshotStateList<T> =
        ListSerializer(itemSerializer).deserialize(decoder).toMutableStateList()

    override fun serialize(encoder: Encoder, value: SnapshotStateList<T>) =
        ListSerializer(itemSerializer).serialize(encoder, value.toList())
}