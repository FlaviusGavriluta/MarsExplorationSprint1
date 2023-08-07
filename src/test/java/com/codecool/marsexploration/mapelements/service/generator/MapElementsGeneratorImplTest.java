package com.codecool.marsexploration.mapelements.service.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class MapElementsGeneratorImplTest {

    @Test
    public void testCreateAll() {
        MapElementBuilder mapElementBuilder = mock(MapElementBuilder.class);
        MapConfiguration mapConfig = mock(MapConfiguration.class);

        MapElement mockElement = mock(MapElement.class);
        when(mapElementBuilder.build(20,"#","mountain",3,"")).thenReturn(mockElement);

        MapElementsGeneratorImpl generator = new MapElementsGeneratorImpl(mapElementBuilder);
        Iterable<MapElement> elements = generator.createAll(mapConfig);

        // Validate the created elements
        Iterator<MapElement> iterator = elements.iterator();
        assertEquals(mockElement, iterator.next());
        // ... additional assertions based on your configuration
    }
}
