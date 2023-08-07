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
/*
package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MapElementsGeneratorImplTest {

    @Test
    public void testCreateAll() {
        MapElementBuilder mockBuilder = mock(MapElementBuilder.class);
        when(mockBuilder.build(anyInt(), anyString(), anyString(), anyInt(), anyString())).thenReturn(new MapElement(new String[2][2], "name", 2));

        List<MapElementConfiguration> elementConfigs = List.of(
                new MapElementConfiguration("symbol", "name", List.of(new ElementToSize(2, 20)), 3, "")
        );
        MapConfiguration mapConfig = new MapConfiguration(17, 0.5, elementConfigs);

        MapElementsGenerator generator = new MapElementsGeneratorImpl(mockBuilder);
        Iterable<MapElement> elements = generator.createAll(mapConfig);

        int count = 0;
        for (MapElement element : elements) {
            count++;
        }

        assertEquals(20, count);
        verify(mockBuilder, times(20)).build(2, "symbol", "name", 3, "");
    }
}

 */
