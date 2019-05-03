package algorithms;

import entity.Graph;
import entity.Vertex;
import org.junit.Test;

import java.util.List;

import static helper.GraphParser.importGraphFromFile;

public class DoppelteBaeumeTest {

    @Test
    public void testDoppelteBaueme_3Knoten() {

        //given
        Graph graph = importGraphFromFile("src/main/resources/p3/vollstaendigerGraph3Knoten.txt", false);

        //when
        List<Vertex> vertices = DoppelteBaeume.runDoppelteBaume(graph, 0, true);

        //then
        DoppelteBaeume.drawTour(vertices);
    }

    @Test
    public void testDoppelteBaueme_4Knoten() {

        //given
        Graph graph = importGraphFromFile("src/main/resources/p3/vollstaendigerGraph4Knoten.txt", false);

        //when
        List<Vertex> vertices = DoppelteBaeume.runDoppelteBaume(graph, 0, true);

        //then
        DoppelteBaeume.drawTour(vertices);
    }

}