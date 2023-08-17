package com.summer.a.provider

import com.google.auto.service.AutoService
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.type.TypeMirror
import javax.tools.FileObject
import javax.tools.StandardLocation
import kotlin.collections.ArrayList

/**
 * 断点进不去就删掉build
 */
@AutoService(Processor::class)

class ProviderProcessor: AbstractProcessor() {

    var filer:Filer? = null

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        filer = processingEnv?.filer
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        var set = mutableSetOf(ProviderAnno::class.java.canonicalName)
        return set
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }

    override fun process(mutableSet: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        var list:MutableList<MutableMap<String,String>> = mutableListOf()
        mutableSet?.forEach {
                set->
            var element = roundEnvironment?.getElementsAnnotatedWith(set)
            element?.forEach {
                if(it.kind!=ElementKind.CLASS){
                    return@forEach
                }
                var providerAnno:ProviderAnno = it.getAnnotation(ProviderAnno::class.java)
                var value: String? = null
                try {
                    value = providerAnno.value.toString()
                } catch (e: MirroredTypeException) {
                    value = e.typeMirror.toString()
                }
                var typeElement = it as TypeElement
                value?.apply {
                    list.add(mutableMapOf(value to typeElement.qualifiedName.toString()))
                }
            }
        }
        generatorClass(list)
        return true
    }

    fun generatorClass(list:MutableList<MutableMap<String,String>>){

        var file: FileObject? =null
        try {
            file = filer?.createResource(StandardLocation.SOURCE_OUTPUT,"","com.summer.a.provider.ProviderManager.kt")
        } catch (e: Exception) {
            e
        }
        var writer = file?.openWriter()
        writer?.apply {
            writer.write("package com.summer.a.lib.provider\n")
            writer.write("class ProviderManager {\n")
            writer.write("companion object{\n")
            writer.write("private var map:MutableMap<Class<*>,Any> = mutableMapOf()\n")
            writer.write("init {\n")
            list.forEach {
                writer.write("map.put(${it.keys.first()}::class.java,${it.values.first()}())\n")
            }
            writer.write("}\n")
            writer.write("fun <T> getProvider(t:Class<T>):T?{\n")
            writer.write("return map[t] as? T\n")
            writer.write("}\n")
            writer.write("}\n")
            writer.write("}\n")
            writer.flush()
            writer.close()
        }

    }
}