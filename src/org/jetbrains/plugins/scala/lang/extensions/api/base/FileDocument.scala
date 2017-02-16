package org.jetbrains.plugins.scala.lang.extensions.api.base

/**
  * User: Dmitry.Naydanov
  * Date: 16.02.17.
  */
trait FileDocument {
  def text: CharSequence
  def owner: SourceFile
}
