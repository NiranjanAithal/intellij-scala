package org.jetbrains.plugins.scala.lang.transformation.annotations

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.extensions.{&&, Parent, PsiElementExt}
import org.jetbrains.plugins.scala.lang.psi.api.base.patterns.ScReferencePattern
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScPatternDefinition
import org.jetbrains.plugins.scala.lang.psi.types.result.Typeable
import org.jetbrains.plugins.scala.lang.transformation._

/**
  * @author Pavel Fatin
  */
class AddTypeToValueDefinition extends AbstractTransformer {
  def transformation(implicit project: Project): PartialFunction[PsiElement, Unit] = {
    case (_: ScReferencePattern) && Parent(l@Parent(_: ScPatternDefinition)) && Typeable(t)
      if !l.nextSibling.exists(_.getText == ":") =>

      appendTypeAnnotation(l, t)
  }
}
