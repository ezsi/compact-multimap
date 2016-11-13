package com.github.bezsias.multimap.scala

import com.github.bezsias._

case class CompactMultiMap[K, V] private (map: multimap.MultiMap[K, V]) extends MultiMap[K, V] {
  import collection.JavaConverters._

  override def size: Int = map.size

  override def memoryUsage(): Int = map.memoryUsage()

  override def isEmpty: Boolean = map.isEmpty

  override def contains(key: K): Boolean = map.contains(key)

  override def contains(key: K, value: V): Boolean = map.contains(key, value)

  override def keys: Set[K] = map.keys().asScala.toSet

  override def put(key: K, value: V): Unit = map.put(key, value)

  override def putAll(key: K, values: List[V]): Unit = map.putAll(key, values.asJava)

  override def get(key: K): List[V] = map.get(key).asScala.toList

  override def remove(key: K): Unit = map.remove(key)

  override def remove(key: K, value: V): Unit = map.remove(key, value)

  override def clear(): Unit = map.clear()
}

object CompactMultiMap {

  def objMap[K, V <: java.io.Serializable](blockSizeKb: Int = 8): CompactMultiMap[K, V] =
    new CompactMultiMap[K, V](multimap.CompactMultiMap.objMultiMap[K, V](blockSizeKb))

  def intMap[K](blockSizeKb: Int = 8): CompactMultiMap[K, Int] = {
    new CompactMultiMap[K, Int](
      multimap.CompactMultiMap.intMultiMap[K](blockSizeKb).asInstanceOf[multimap.MultiMap[K, Int]]
    )
  }

  def shortMap[K](blockSizeKb: Int = 8): MultiMap[K, Short] = {
    new CompactMultiMap[K, Short](
      multimap.CompactMultiMap.shortMultiMap[K](blockSizeKb).asInstanceOf[multimap.MultiMap[K, Short]]
    )
  }

}
