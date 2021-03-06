package org.jetbrains.plugins.scala.base

import com.intellij.openapi.Disposable
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import org.jetbrains.plugins.scala.base.libraryLoaders.{JdkLoader, ScalaLibraryLoader}
import org.jetbrains.plugins.scala.debugger.DebuggerTestUtil.findJdk8
import org.jetbrains.plugins.scala.util.TestUtils

/** Special version of library loader whose lifecycle is managed automatically by IDEA disposer mechanism.
  *
  * Loader will be disposed together with the module it's attached to.
  */
class DisposableScalaLibraryLoader(implicit project: Project, module: Module)
  extends ScalaLibraryLoader(true) with Disposable {

  Disposer.register(module, this)

  private val jdkLoader = JdkLoader(findJdk8())

  override def init(implicit version: TestUtils.ScalaSdkVersion): Unit = {
    super.init
    jdkLoader.init
  }

  override def clean(): Unit = {}

  override def dispose(): Unit = clean()
}
