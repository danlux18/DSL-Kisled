package dsl.kisled.demo;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import dsl.kisled.demo.psi.KisledTypes;
import org.jetbrains.annotations.NotNull;

public class KisledCompletionContributor extends CompletionContributor {
    public KisledCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(KisledTypes.NAME),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("actuator"));
                        resultSet.addElement(LookupElementBuilder.create("sensor"));
                        resultSet.addElement(LookupElementBuilder.create("ON"));
                        resultSet.addElement(LookupElementBuilder.create("SHORT"));
                        resultSet.addElement(LookupElementBuilder.create("LONG"));
                        resultSet.addElement(LookupElementBuilder.create("OFF"));
                        resultSet.addElement(LookupElementBuilder.create("HIGH"));
                        resultSet.addElement(LookupElementBuilder.create("LOW"));
                    }
                }
        );
    }
}
