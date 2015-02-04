package com.github.fge.grappa.debugger.csvtrace.tabs.stats;

import com.github.fge.grappa.debugger.common.JavafxView;
import com.github.fge.grappa.debugger.csvtrace.newmodel.ParseInfo;
import com.github.fge.grappa.debugger.javafx.JavafxUtils;
import com.github.fge.grappa.matchers.MatcherType;
import javafx.scene.chart.PieChart;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public final class JavafxStatsTabView
    extends JavafxView<StatsTabPresenter, StatsTabDisplay>
    implements StatsTabView
{
    public JavafxStatsTabView()
        throws IOException
    {
        super("/tabs/statsTab.fxml");
    }

    @Override
    public void showParseInfo(final ParseInfo info)
    {
        Objects.requireNonNull(info);

        display.parseDate.setText(info.getTime().toString());

        double ratio;

        final int nrInvocations = info.getNrInvocations();
        display.nrRules.setText(String.valueOf(nrInvocations));

        ratio = (double) nrInvocations / info.getNrLines();
        display.invPerLine.setText(String.format("%.2f", ratio));

        ratio = (double) nrInvocations / info.getNrChars();
        display.invPerChar.setText(String.format("%.2f", ratio));


        display.treeDepth.setText(String.valueOf(info.getTreeDepth()));
    }

    @Override
    public void displayTotalParseTime(final long totalParseTime)
    {
        display.totalParseTime.setText(
            JavafxUtils.nanosToString(totalParseTime));
    }

    @Override
    public void displayMatchersByType(
        final Map<MatcherType, Integer> matchersByType)
    {
        final Function<MatcherType, Integer> zero = t -> 0;
        final int totalMatchers = matchersByType.values().stream()
            .mapToInt(Integer::intValue).sum();

        final List<PieChart.Data> list = new ArrayList<>(4);

        MatcherType type;
        int nr;
        double pct;
        String fmt;
        PieChart.Data data;

        type = MatcherType.TERMINAL;
        nr = matchersByType.computeIfAbsent(type, zero);
        pct = 100.0 * nr / totalMatchers;
        fmt = String.format("%s (%d; %.02f%%)", type, nr, pct);
        data = new PieChart.Data(fmt, pct);
        list.add(data);

        type = MatcherType.COMPOSITE;
        nr = matchersByType.computeIfAbsent(type, zero);
        pct = 100.0 * nr / totalMatchers;
        fmt = String.format("%s (%d; %.02f%%)", type, nr, pct);
        data = new PieChart.Data(fmt, pct);
        list.add(data);

        type = MatcherType.PREDICATE;
        nr = matchersByType.computeIfAbsent(type, zero);
        pct = 100.0 * nr / totalMatchers;
        fmt = String.format("%s (%d; %.02f%%)", type, nr, pct);
        data = new PieChart.Data(fmt, pct);
        list.add(data);

        type = MatcherType.ACTION;
        nr = matchersByType.computeIfAbsent(type, zero);
        pct = 100.0 * nr / totalMatchers;
        fmt = String.format("%s (%d; %.02f%%)", type, nr, pct);
        data = new PieChart.Data(fmt, pct);
        list.add(data);

        display.matchersChart.getData().setAll(list);

        fmt = String.format("Matchers by type (%d total)", totalMatchers);

        display.matchersChart.setTitle(fmt);
    }
}
