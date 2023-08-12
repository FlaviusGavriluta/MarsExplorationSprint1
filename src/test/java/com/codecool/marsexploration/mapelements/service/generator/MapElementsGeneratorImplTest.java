package com.codecool.marsexploration.mapelements.service.generator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class MapElementsGeneratorImplTest {
    private MapElementsGeneratorImpl generator;
    private MapElementBuilder builder;

    @BeforeEach
    void setUp() {
        builder = mock(MapElementBuilder.class);
        generator = new MapElementsGeneratorImpl(builder);
    }

//    @Test
//    void testCreateAll() {
////        MapConfiguration mapConfig = mock(MapConfiguration.class);
////
////        MapElement mockElement = mock(MapElement.class);
////        when(builder.build(20, "#", "mountain", 3, "")).thenReturn(mockElement);
////
////        Iterable<MapElement> elements = generator.createAll(mapConfig);
////
////        // Validate the created elements
////        Iterator<MapElement> iterator = elements.iterator();
////        assertEquals(mockElement, iterator.next());
////        // ... additional assertions based on your configuration
//    }

    @Test
    void testIsUnidimensionalForUnidimensionalConfig() {
        MapElementConfiguration config = new MapElementConfiguration(
                "%",
                "mineral",
                List.of(new ElementToSize(1, 1)),
                0,
                "#"
        );

       assertTrue(generator.isUnidimensional(config), "Expected the configuration to be unidimensional");
    }

//    @Test
//    void testIsUnidimensionalForMultidimensionalConfig() {
//        MapElementConfiguration config = new MapElementConfiguration(
//                "#",
//                "mountain",
//                List.of(new ElementToSize(2, 20)),
//                3,
//                ""
//        );
//
//        assertFalse(generator.isUnidimensional(config), "Expected the configuration to be multidimensional");
//    }
//
//
//    @Test
//    void testGenerateUnidimensionalElements() {
//        MapElementConfiguration config = new MapElementConfiguration(
//                "%",
//                "mineral",
//                List.of(new ElementToSize(1, 5)),
//                0,
//                "#"
//        );
//
//        MapElement mockElement = new MapElement(new String[][]{{"%"}}, "mineral", 1, "#");
//        when(builder.build(1, "%", "mineral", 0, "#")).thenReturn(mockElement);
//
//        List<MapElement> generatedElements = generator.generateUnidimensionalElements(config);
//
//        assertEquals(5, generatedElements.size(), "Expected 5 elements to be generated");
//        assertTrue(generatedElements.stream().allMatch(e -> e.getRepresentation()[0][0].equals("%")), "All generated elements should represent minerals");
//    }
//
//    @Test
//    void testGenerateMultidimensionalElements() {
//        MapElementConfiguration config = new MapElementConfiguration(
//                "#",
//                "mountain",
//                List.of(new ElementToSize(2, 3)),
//                3,
//                ""
//        );
//
//        MapElement mockElement = new MapElement(new String[][]{{"#", "#"}, {"#", "#"}}, "mountain", 2);
//        when(builder.build(2, "#", "mountain", 3, "")).thenReturn(mockElement);
//
//        List<MapElement> generatedElements = generator.generateMultidimensionalElements(config);
//
//        assertEquals(3, generatedElements.size(), "Expected 3 elements to be generated");
//        assertTrue(generatedElements.stream().allMatch(e -> e.getRepresentation().length == 2), "All generated elements should represent mountains of size 2x2");
//    }
}
