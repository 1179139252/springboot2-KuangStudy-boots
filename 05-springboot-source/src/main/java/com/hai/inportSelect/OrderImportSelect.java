package com.hai.inportSelect;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class OrderImportSelect implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.hai.inportSelect.OrderBean",
                "com.hai.inportSelect.OrderServiceBean",
            };
    }
}
