package com.github.fge.grappa.debugger.csvtrace.tabs.tree;

import com.github.fge.grappa.debugger.javafx.JavafxDisplay;
import com.github.fge.grappa.debugger.javafx.parsetree.ParseTreeItem;
import com.github.fge.grappa.debugger.javafx.parsetree.ParseTreeNodeCell;
import com.github.fge.grappa.debugger.model.ParseTreeNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.text.TextFlow;

public class TreeTabDisplay
    extends JavafxDisplay<TreeTabPresenter>
{
    /*
     * Tree
     */
    @FXML
    protected Label treeInfo;

    @FXML
    protected TreeView<ParseTreeNode> parseTree;

    /*
     * Node detail
     */
    @FXML
    protected Label nodeDepth;

    @FXML
    protected Label nodeRuleName;

    @FXML
    protected Label nodeMatcherType;

    @FXML
    protected Label nodeMatcherClass;

    @FXML
    protected Label nodeStatus;

    @FXML
    protected Label nodeStartPos;

    @FXML
    protected Label nodeEndPos;

    @FXML
    protected Label nodeTime;

    /*
     * Text
     */
    @FXML
    protected Label textInfo;

    @FXML
    protected TextFlow inputText;

    @FXML
    protected ScrollPane inputTextScroll;

    protected ParseTreeItem currentItem;

    @Override
    public void init()
    {
        parseTree.setCellFactory(param -> new ParseTreeNodeCell(this));
    }

    public void parseTreeNodeShowEvent(final ParseTreeNode node)
    {
        presenter.handleParseTreeNodeShow(node);
    }

    public void needChildrenEvent(final ParseTreeItem parseTreeItem)
    {
        currentItem = parseTreeItem;
        presenter.handleNeedChildren(currentItem.getValue());
    }
}
