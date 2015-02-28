package com.github.fge.grappa.debugger.csvtrace.tabs.treedepth;

import java.util.Map;

public interface TreeDepthTabView
{
    void disableToolbar();

    void setMaxLines(int nrLines);

    void displayChart(Map<Integer, Integer> depthMap);

    void updateStartLine(int startLine);

    void updateToolbar(boolean disablePrev, boolean disableNext);
}
