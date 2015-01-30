package com.github.fge.grappa.debugger.tracetab;

import com.github.fge.grappa.debugger.common.JavafxDisplay;
import com.github.fge.grappa.debugger.internal.NotFXML;
import com.github.fge.grappa.debugger.javafx.Utils;
import com.github.fge.grappa.debugger.stats.ParseNode;
import com.github.fge.grappa.trace.TraceEvent;
import com.github.fge.grappa.trace.TraceEventType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TraceTabDisplay
    extends JavafxDisplay<TraceTabPresenter>
{
    /*
     * Tree tab
     */
    @FXML
    protected Button treeExpand;

    @FXML
    TreeView<ParseNode> parseTree;

    @FXML
    protected Label parseNodeLevel;

    @FXML
    protected Label parseNodeRuleName;

    @FXML
    protected Label parseNodeMatcherType;

    @FXML
    protected Label parseNodeMatcherClass;

    @FXML
    protected Label parseNodeStatus;

    @FXML
    protected Label parseNodeStart;

    @FXML
    protected Label parseNodeEnd;

    @FXML
    protected Label parseNodeTime;

    @FXML
    protected Label textInfo;

    @FXML
    protected TextFlow inputText;

    @FXML
    protected ScrollPane inputTextScroll;

    /*
     * Stats tabs
     */
    @FXML
    protected Tab globalStatsTab;

    @FXML
    protected Tab classStatsTab;
    /*
     * Events tab
     */
    @FXML
    protected Tab eventsTab;

    /*
     * Events table
     */
    @FXML
    protected TableView<TraceEvent> events;

    @FXML
    protected TableColumn<TraceEvent, Long> eventTime;

    @FXML
    protected TableColumn<TraceEvent, String> eventRule;

    @FXML
    protected TableColumn<TraceEvent, Integer> eventIndex;

    @FXML
    protected TableColumn<TraceEvent, TraceEventType> eventType;

    @FXML
    protected TableColumn<TraceEvent, Integer> eventDepth;

    @FXML
    protected TableColumn<TraceEvent, String> eventPath;

    @Override
    public void init()
    {
        /*
         * Parse tree
         */
        parseTree.setCellFactory(param -> new ParseNodeCell(this));

        /*
         * Trace events
         */
        bindColumn(eventTime, "nanoseconds");
        setDisplayNanos(eventTime);
        bindColumn(eventDepth, "level");
        bindColumn(eventIndex, "index");
        bindColumn(eventPath, "path");
        bindColumn(eventRule, "matcher");
        bindColumn(eventType, "type");
        eventType.setCellFactory(
            param -> new TableCell<TraceEvent, TraceEventType>()
            {
                @Override
                protected void updateItem(final TraceEventType item,
                    final boolean empty)
                {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        return;
                    }
                    final Text text = new Text(item.name());
                    switch (item) {
                        case MATCH_FAILURE:
                            text.setFill(Color.RED);
                            break;
                        case MATCH_SUCCESS:
                            text.setFill(Color.GREEN);
                    }
                    setGraphic(text);
                }
            });
    }

    @FXML
    void expandParseTreeEvent(final Event event)
    {
        presenter.handleExpandParseTree();
    }

    @NotFXML
    void parseNodeShowEvent(final ParseNode node)
    {
        presenter.handleParseNodeShow(node);
    }

    private static <S, T> void bindColumn(final TableColumn<S, T> column,
        final String propertyName)
    {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    }

    private static <S> void setDisplayNanos(final TableColumn<S, Long> column)
    {
        column.setCellFactory(param -> new TableCell<S, Long>()
        {
            @Override
            protected void updateItem(final Long item, final boolean empty)
            {
                super.updateItem(item, empty);
                //noinspection AutoUnboxing
                setText(empty ? null : Utils.nanosToString(item));
            }
        });
    }

    private static final class ParseNodeCell
        extends TreeCell<ParseNode>
    {
        private ParseNodeCell(final TraceTabDisplay display)
        {
            setEditable(false);
            selectedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(
                    final ObservableValue<? extends Boolean> observable,
                    final Boolean oldValue, final Boolean newValue)
                {
                    if (!newValue)
                        return;
                    final ParseNode node = getItem();
                    if (node != null)
                        display.parseNodeShowEvent(node);
                }
            });
        }

        @Override
        protected void updateItem(final ParseNode item, final boolean empty)
        {
            super.updateItem(item, empty);
            setText(empty ? null : String.format("%s (%s)", item.getRuleName(),
                item.isSuccess() ? "SUCCESS" : "FAILURE"));
        }
    }
}
