package org.jetbrains.plugins.scala
package lang
package psi
package stubs
package impl

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{IStubElementType, StubBase, StubElement}
import com.intellij.util.io.StringRef
import org.jetbrains.plugins.scala.lang.psi.api.base.ScAccessModifier

/**
  * User: Alexander Podkhalyuzin
  * Date: 17.06.2009
  */
class ScAccessModifierStubImpl[ParentPsi <: PsiElement](parent: StubElement[ParentPsi],
                                                        elementType: IStubElementType[_ <: StubElement[_ <: PsiElement], _ <: PsiElement],
                                                        val isProtected: Boolean,
                                                        val isPrivate: Boolean,
                                                        val isThis: Boolean,
                                                        private val idTextRef: Option[StringRef])
  extends StubBase[ScAccessModifier](parent, elementType) with ScAccessModifierStub {

  def idText: Option[String] = idTextRef.map(StringRef.toString)
}