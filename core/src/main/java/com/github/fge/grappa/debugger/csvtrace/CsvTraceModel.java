package com.github.fge.grappa.debugger.csvtrace;

import com.github.fge.grappa.debugger.GrappaDebuggerException;
import com.github.fge.grappa.debugger.model.tabs.tree.InputText;
import com.github.fge.grappa.debugger.model.common.ParseInfo;
import com.github.fge.grappa.debugger.model.tabs.tree.ParseTree;
import com.github.fge.grappa.debugger.model.tabs.tree.ParseTreeNode;
import com.github.fge.grappa.debugger.model.tabs.rules.PerClassStatistics;
import com.github.fge.grappa.debugger.model.tabs.matches.MatchesData;
import com.github.fge.grappa.matchers.MatcherType;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public interface CsvTraceModel
{
    boolean isLoadComplete();

    void waitForNodes()
        throws GrappaDebuggerException;

    void waitForMatchers()
        throws GrappaDebuggerException;

    @Nonnull
    ParseInfo getParseInfo();

    @Nonnull
    InputText getInputText()
        throws GrappaDebuggerException;

    @Nonnull
    ParseTree getParseTree()
        throws GrappaDebuggerException;

    @Nonnull
    List<ParseTreeNode> getNodeChildren(int nodeId)
        throws GrappaDebuggerException;

    @Nonnull
    ParseTreeNode getNodeById(int id)
        throws GrappaDebuggerException;

    @Nonnull
    Map<MatcherType, Integer> getMatchersByType()
        throws GrappaDebuggerException;

    @Nonnull
    List<PerClassStatistics> getRulesByClass()
        throws GrappaDebuggerException;

    void dispose()
        throws GrappaDebuggerException;

    @Nonnull
    Map<Integer, Integer> getDepthMap(int startLine, int wantedLines)
        throws GrappaDebuggerException;

    @Nonnull
    MatchesData getMatchesData();
}
