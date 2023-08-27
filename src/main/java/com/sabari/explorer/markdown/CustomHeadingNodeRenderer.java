package com.sabari.explorer.markdown;

import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlWriter;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.node.Heading;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Node;

import java.util.Collections;
import java.util.Set;

public class CustomHeadingNodeRenderer extends AbstractVisitor implements NodeRenderer {
    private HtmlWriter htmlWriter;
    private HtmlNodeRendererContext context;
    
    public CustomHeadingNodeRenderer(HtmlNodeRendererContext context) {
        this.context = context;
        this.htmlWriter = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        // Return the node types we want to use this renderer for.
        return Collections.<Class<? extends Node>>singleton(Heading.class);
    }

    @Override
    public void visit (Heading heading) {
        String htag = "h" + heading.getLevel();
        htmlWriter.line();
        htmlWriter.tag(htag);
        visitChildren(heading);
        htmlWriter.tag('/' + htag);
        htmlWriter.line();

        // Adding a horizontal ruler after each heading
        htmlWriter.tag("hr");
        htmlWriter.tag("/hr");
    }

    @Override
    public void render (Node node) {
        node.accept(this);
    }

    @Override
    protected void visitChildren(Node parent) {
        Node node = parent.getFirstChild();
        while (node != null) {
            Node next = node.getNext();
            context.render(node);
            node = next;
        }
    }
}
