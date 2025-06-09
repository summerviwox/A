package com.summer.database.album

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * @param id 9994
 * @param ctime 1553763041000
 * @param isUploaded 0 1
 * @param locpath /storage/emulated/0/tencent/MicroMsg/WeChat/1553763040226.mp4
 * @param netpath E:\record\20190328\1553763040226.mp4
 * @param atype video
 * @param
 */
@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val ctime:Long,
    val netpath:String?,
    val locpath:String?,
    val atype:String?,
){
    var top:Int = 0
    var empty:Int = 0
    @Ignore
    var folders:MutableMap<String,Folder>? = null
    @Ignore
    var folderList:MutableList<Folder>? = null
    @Ignore
    var position:Int = 0
}

/**
 * @param type 0 文件夹 1 文件
 */
data class Folder(
    val name:String,
    var type:Int,
    val item: Item?,
    var folders:MutableMap<String,Folder>?,
    var folderList:MutableList<Folder>? = null,
    var items:MutableList<Item>?=null
)

@Dao
interface ItemDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:Item)
    @Update
    suspend fun update(item: Item)
    @Delete
    suspend fun delete(item: Item)
    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    @Query("SELECT * from item order by ctime asc")
    fun getAll():MutableList<Item>
}
