import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.StandardOpenOption

class DeleteSDKSOTask extends DefaultTask{


    @TaskAction
    void aaa(){
        println("DeleteSDKSOTask aaaaaaaaaaaaaaaaa")

        File file = new File(getProject().projectDir,"/build/intermediates/merged_native_libs/debug/out/lib")
        if(!file.exists()){
            return
        }
        println("DeleteSDKSOTask - 删除文件 - ")
        file.listFiles().each { paltform ->
            if(paltform.isDirectory()){
                paltform.listFiles().each { soFile->
                    //直接删除 会报错 StripDebugSymbolsTask NoSuchFileException 这里把这个文件删除在创建等于清除数据
                    soFile.delete()
                    soFile.createNewFile()
                    println(soFile.path)
                }
            }
        }
    }
}