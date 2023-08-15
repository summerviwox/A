package com.summer.a.provider

import com.google.auto.service.AutoService
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
/**
 * 断点进不去就删掉build
 */
@AutoService(Processor::class)
class ProviderProcessor: AbstractProcessor() {

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        var set = mutableSetOf(ProviderAnno::class.java.canonicalName)
        return set
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }

    override fun process(mutableSet: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        println("1111111111111111111111")
//        mutableSet?.forEach {
//            set->
//            var element = roundEnvironment?.getElementsAnnotatedWith(set)
//
//        }
//        var set = roundEnvironment?.getElementsAnnotatedWith(ProviderAnno::class.java)
//        set?.forEach {
//            var TypeElement = it as TypeElement
//            var c = Class.forName(TypeElement.qualifiedName.toString())
//            var o = c.getDeclaredConstructor().newInstance()
//
//        }
        return true
    }
}