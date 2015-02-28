package com.github.fge.grappa.debugger.csvtrace.tabs.tree;

import com.github.fge.grappa.debugger.GrappaDebuggerException;
import com.github.fge.grappa.debugger.common.GuiTaskRunner;
import com.github.fge.grappa.debugger.csvtrace.CsvTraceModel;
import com.github.fge.grappa.debugger.model.InputText;
import com.github.fge.grappa.debugger.model.ParseTree;
import com.github.fge.grappa.debugger.model.ParseTreeNode;
import com.github.fge.grappa.debugger.mainwindow.MainWindowView;
import com.google.common.util.concurrent.MoreExecutors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TreeTabPresenterTest
{
    private final GuiTaskRunner taskRunner = new GuiTaskRunner(
        MoreExecutors.newDirectExecutorService(), Runnable::run
    );

    private MainWindowView mainView;
    private CsvTraceModel model;
    private TreeTabPresenter presenter;
    private TreeTabView view;

    @BeforeMethod
    public void init()
    {
        mainView = mock(MainWindowView.class);
        model = mock(CsvTraceModel.class);
        presenter = spy(new TreeTabPresenter(taskRunner, mainView, model));

        view = mock(TreeTabView.class);
        presenter.setView(view);
    }

    @Test
    public void loadTest()
    {
        doNothing().when(presenter).loadInputText();
        doNothing().when(presenter).loadParseTree();

        presenter.load();

        verify(presenter).loadParseTree();
        verify(presenter).loadInputText();
    }

    @Test
    public void loadParseTreeSuccessTest()
        throws GrappaDebuggerException
    {
        final ParseTree parseTree = mock(ParseTree.class);
        when(model.getParseTree()).thenReturn(parseTree);

        presenter.loadParseTree();

        verify(model).getParseTree();
        verify(view).loadParseTree(same(parseTree));
    }

    @Test
    public void loadParseTreeErrorTest()
        throws GrappaDebuggerException
    {
        final Exception cause = new Exception();
        final GrappaDebuggerException exception
            = new GrappaDebuggerException(cause);
        when(model.getParseTree()).thenThrow(exception);

        presenter.loadParseTree();

        verify(model).getParseTree();
        verify(view, never()).loadParseTree(any());
        verify(presenter).handleLoadParseTreeError(same(exception));
    }

    @Test
    public void loadInputTextSuccessTest()
        throws GrappaDebuggerException
    {
        final InputText inputText = mock(InputText.class);
        when(model.getInputText()).thenReturn(inputText);

        presenter.loadInputText();

        verify(model).getInputText();
        verify(view).loadInputText(same(inputText));
    }

    @Test
    public void loadInputTextErrorTest()
        throws GrappaDebuggerException
    {
        final Exception cause = new Exception();
        final GrappaDebuggerException exception
            = new GrappaDebuggerException(cause);
        when(model.getInputText()).thenThrow(exception);

        presenter.loadInputText();

        verify(model).getInputText();
        verify(presenter).handleLoadInputTextError(same(exception));
        verify(view, never()).loadInputText(any());
    }


    @SuppressWarnings("AutoBoxing")
    @Test
    public void handleParseTreeNodeShowFailureTest()
    {
        final int end = 42;

        final ParseTreeNode node = mock(ParseTreeNode.class);
        when(node.isSuccess()).thenReturn(false);
        when(node.getEndIndex()).thenReturn(end);

        presenter.handleParseTreeNodeShow(node);

        verify(view).showParseTreeNode(same(node));
        verify(view).highlightFailure(end);
    }

    @SuppressWarnings("AutoBoxing")
    @Test
    public void handleParseTreeNodeShowSuccessTest()
    {
        final int start = 24;
        final int end = 42;

        final ParseTreeNode node = mock(ParseTreeNode.class);
        when(node.isSuccess()).thenReturn(true);
        when(node.getStartIndex()).thenReturn(start);
        when(node.getEndIndex()).thenReturn(end);

        presenter.handleParseTreeNodeShow(node);

        verify(view).showParseTreeNode(same(node));
        verify(view).highlightSuccess(start, end);
    }
}
