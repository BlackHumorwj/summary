package com.kingboy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class BuildSrcGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.tasks.forEach {
            // System.out.println("打印的==>  ${it.name}")

        }

        //gradle执行会解析build.gradle文件，afterEvaluate表示在解析完成之后再执行我们的代码
        project.afterEvaluate {

            //System.out.println("打印的==>  ${it?.name}")

//            project.tasks.forEach {
//                  System.out.println("打印的==>  ${it.name}")
//            }


            var task = it.tasks.findByName("assembleDebug")

            System.out.println("打印的==>  ${task?.name}")

            // dolast：在这个任务之后再干一些事情
            // 在混淆后备份mapping
            task?.doLast {

                //编译任务输出的所有文件
                val outputs = it.outputs
                val files = outputs.files.files

                System.out.println("打印的==>  ${files.size}")


                files.forEach { file ->
                    System.out.println("打印的==>  ${file?.name}")
                }

            }


        }



        updateAppName(project)

    }

    private fun updateAppName(project: Project) {
   //配置参数以apkrename{}进行配置
      //  val extension = project.extensions.create("apkrename", ApkRenamePluginExtension::class.java)
        // 应用名称
      //  val projectName = project.name


        // 打包日期，格式为：年月日时分秒
        //var date =   Date()



        project.afterEvaluate {



        }

        var buildTime = ""
        // project有很多扩展信息，我们这里需要Android相关的扩展信息，com.android.build.gradle.AppExtension
         // var appExtension = project.extensions.getByType(AppExtension)



        // 遍历扩展信息里的所有变体，根据每个变体的信息组合出Apk的文件名称
//        appExtension.applicationVariants.all { variant ->
//            // 通过方法定义我们知道variant的实际类型为ApplicationVariant
//            // val applicationVariants: DomainObjectSet<ApplicationVariant> =
//            //         dslServices.domainObjectSet(ApplicationVariant::class.java)
//            var versionCode = ((ApplicationVariant) variant).versionCode
//            var versionName = ((ApplicationVariant) variant).versionName
//            var buildType = ((ApplicationVariant) variant).buildType.name
//            var flavor = ((ApplicationVariant) variant).flavorName
//            ((ApplicationVariant) variant).outputs.all { output ->
//                // 变体输出的除了apk还有其他文件，这里我们只修改apk的文件名
//                var outputFile = ((BaseVariantOutput) output).outputFile
//                if (outputFile != null && outputFile.name.endsWith(".apk")) {
//                    ((ApkVariantOutput) output).outputFileName =
//                        "$projectName-$versionCode-$versionName-$buildType-$flavor-${buildTime}.apk"
//                }
//            }
//        }

    }
}