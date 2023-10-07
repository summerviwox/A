//package com.summer.a.provider
//
//import com.google.auto.service.AutoService
//import com.squareup.javapoet.JavaFile
//import com.squareup.javapoet.MethodSpec
//import com.squareup.javapoet.TypeSpec
//import java.io.IOException
//import javax.annotation.processing.*
//import javax.lang.model.SourceVersion
//import javax.lang.model.element.ElementKind
//import javax.lang.model.element.Modifier
//import javax.lang.model.element.TypeElement
//import javax.lang.model.type.MirroredTypeException
//import javax.tools.FileObject
//import javax.tools.StandardLocation
//
//
//@AutoService(Processor::class)
//class RouterProcessor: AbstractProcessor() {
//
//    var filer:Filer? = null
//
//    override fun init(processingEnv: ProcessingEnvironment?) {
//        super.init(processingEnv)
//        filer = processingEnv?.filer
//    }
//
//    override fun getSupportedAnnotationTypes(): MutableSet<String> {
//        var set = mutableSetOf(RouteAnno::class.java.canonicalName)
//        return set
//    }
//
//    override fun getSupportedSourceVersion(): SourceVersion {
//        return super.getSupportedSourceVersion()
//    }
//
//
//    override fun process(mutableSet: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
//        var list:MutableList<MutableMap<String,String>> = mutableListOf()
//        mutableSet?.forEach {
//                set->
//            var element = roundEnvironment?.getElementsAnnotatedWith(set)
//            element?.forEach {
//                if(it.kind!= ElementKind.CLASS){
//                    return@forEach
//                }
//                var route:RouteAnno = it.getAnnotation(RouteAnno::class.java)
//                var value: String? = null
//                try {
//                    value = route.value.toString()
//                } catch (e: MirroredTypeException) {
//                    //Attempt to access Class object for TypeMirror 获取不到providerAnno.value就trycatch这样处理
//                    value = e.typeMirror.toString()
//                }
//                var typeElement = it as TypeElement
//                value?.apply {
//                    list.add(mutableMapOf(value to typeElement.qualifiedName.toString()))
//
///*                    try {
//                        var methodSpec = MethodSpec.methodBuilder("test")
//                        var builder = methodSpec
//                            .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
//                            .returns(Void.TYPE)
//                            .addStatement("RouterProvider.put(${value},${typeElement.qualifiedName.toString()})")
//                            .build()
//
//                        var testRouterInit = TypeSpec.classBuilder("InitClass")
//                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                            .addMethod(builder)
//                            .build();
//
//                        var javaFile = JavaFile.builder("com.summer.a.router", testRouterInit)
//                        .build();
//                        javaFile.writeTo(filer);
//                    } catch (e: Exception) {
//                        e
//                    }*/
//
//                }
//            }
//        }
//
//
//
//
//        println("${roundEnvironment?.processingOver()} ${roundEnvironment?.errorRaised()}")
//        list.forEach {
//            RouterProvider.put(it.keys.first(),it.values.first())
//            println("############################################")
//            println("${it.keys.first()} ${it.values.first()}")
//            println("############################################")
//        }
//        //generatorClass(list)
//        return true
//    }
//
//    fun generatorClass(list:MutableList<MutableMap<String,String>>){
//
//        var file: FileObject? =null
//        try {
//            //createSourceFile 会创建ProviderManager.kt.java 用createResource
//            file = filer?.createResource(StandardLocation.SOURCE_OUTPUT,"","com.summer.a.router.RouterProvider.kt")
//            var path = file?.toUri()?.path
//            var writer = file?.openWriter()
//            //println("RouterProvider")
//            writer?.apply {
//                writer.write("package com.summer.a.router\n")
//                writer.write("import kotlin.reflect.KClass\n")
//                writer.write("class RouterProvider {\n")
//                writer.write("    companion object{\n")
//                writer.write("var mutableMap = mutableMapOf<String, KClass<*>>()\n")
//                writer.write("init {\n")
//                list.forEach {
//                    println("############################################")
//                    println(path)
//                    println("${it.keys.first()} ${it.values.first()}")
//                    println("############################################")
//                    writer.write("mutableMap.put(\"${it.keys.first()}\",${it.values.first()}::class)\n")
//                }
//
//                writer.write("}\n")
//                writer.write("fun getClass(path:String):KClass<*>?{\n")
//                writer.write("return RouterProvider.mutableMap[path]\n")
//                writer.write("}\n")
//                writer.write("}\n")
//                writer.write("}\n")
//                writer.flush()
//                writer.close()
//            }
//        } catch (e: Exception) {
//            //javax.annotation.processing.FilerException: Attempt to recreate a file for type 重复创建trycatch处理
//            e
//        }
//    }
////    fun generatorClass(list:MutableList<MutableMap<String,String>>){
////
////        var file: FileObject? =null
////        try {
////            //createSourceFile 会创建ProviderManager.kt.java 用createResource
////            file = filer?.createResource(StandardLocation.SOURCE_OUTPUT,"","com.summer.a.router.RouterPath.kt")
////            var path = file?.toUri()?.path
////            var writer = file?.openWriter()
////            println("generatorClass")
////            writer?.apply {
////                writer.write("package com.summer.a.router\n")
////                writer.write("class RouterPath {\n")
////                writer.write("companion object{\n")
////                list.forEach {
////                    println("##### ${it.keys.first()} ${it.values.first()}")
////                    writer.write("val ${it.keys.first().replace("/","_")} =\"${it.keys.first()}\"\n")
////                }
////                writer.write("}\n")
////                writer.write("}\n")
////                writer.flush()
////                writer.close()
////            }
////        } catch (e: Exception) {
////            //javax.annotation.processing.FilerException: Attempt to recreate a file for type 重复创建trycatch处理
////            e
////        }
////    }
//}