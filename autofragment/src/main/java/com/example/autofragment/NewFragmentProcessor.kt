package com.example.autofragment

import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

class NewFragmentProcessor : AbstractProcessor() {
    private var messager: Messager? = null
    private var elementUtil: Elements? = null
    private var filter: Filer? = null
    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        messager = processingEnv?.messager
        elementUtil = processingEnv?.elementUtils
        filter = processingEnv?.filer
    }

    //返回我们讲要出来的注解
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return LinkedHashSet<String>().apply {
            add((NewFragment::class.java).canonicalName)
            messager?.printMessage(Diagnostic.Kind.WARNING, "name = ${(NewFragment::class.java).canonicalName}")
        }
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }


    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        messager?.printMessage(Diagnostic.Kind.WARNING, "日志开始---------------")
        val elementAnnotatedWith = roundEnv?.getElementsAnnotatedWith(NewFragment::class.java)

        if (elementAnnotatedWith.isNullOrEmpty()) {
            return false
        }

        val sourceFile = filter?.createSourceFile(Constants.packageName + "." + Constants.className)
        try {
            val writer = sourceFile?.openWriter();
            writer?.write("package  " + Constants.packageName + ";\n")
            writer?.write("public class " + Constants.className + "" + "  { \n")
            writer?.write("\n")
            writer?.append("public String[] fragments = new String[]{\n")

            elementAnnotatedWith.forEach { element ->

                if (element.kind == ElementKind.CLASS) {

                    val annotation = element.getAnnotation(NewFragment::class.java)
                    messager?.printMessage(Diagnostic.Kind.OTHER, "${annotation.text}")
                    writer?.write("\"${annotation.text}\",\n")

                    val packageElement = elementUtil?.getPackageOf(element)
                    val packagePath = packageElement?.qualifiedName.toString()
                    val className = (element as TypeElement).simpleName.toString()

                    messager?.printMessage(Diagnostic.Kind.OTHER, "${packagePath}.${className}")
                    writer?.write("\"${packagePath}.${className}\",\n")
                }
            }

            writer?.append("};")
            writer?.write("\n")
            writer?.append("}")
            writer?.flush();
            writer?.close();
            messager?.printMessage(Diagnostic.Kind.OTHER, "successful")
        } catch (e: IOException) {
            e.printStackTrace()
            messager?.printMessage(Diagnostic.Kind.OTHER, "fail ..")
        }


        return false
    }
}