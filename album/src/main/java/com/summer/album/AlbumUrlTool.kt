package com.summer.album

import com.summer.database.album.Item

object AlbumUrlTool {

    fun getImageUrl(path:String?):String?{
        if(path==null){
            return path
        }
        return "https://www.summerviwox.com/records/${path.substring("E:\\\\records".length).replace("\\\\","/").replace("\\","/")}"
    }

    fun getImageThumbnailUrl(path:String?,item: Item):String?{
        if(path==null){
            return path
        }
        val length = if(path.startsWith("E:\\records")) "E:\\records".length else "E:\\record".length
        var s = "https://www.summerviwox.com/thumbnail${path.substring(length).replace("\\\\","/").replace("\\","/")}"
        if(item.atype.equals("video")){
            return s.substring(0,s.lastIndexOf("."))+".jpg"
        }
        return s
    }
}