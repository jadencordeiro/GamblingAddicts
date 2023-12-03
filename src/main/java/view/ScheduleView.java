package view;

import navigation.interface_adapter.NavigationController;
import schedule.service.refresh.interface_adapter.RefreshController;
import schedule.service.refresh.interface_adapter.ScheduleState;
import schedule.service.refresh.interface_adapter.ScheduleViewModel;
import user.interface_adapter.LoggedInViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScheduleView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "schedule";
    private final ScheduleViewModel scheduleViewModel;
    private final RefreshController refreshController;
    private final NavigationController navigationController;
    private final JButton refresh;
    private final JButton home;
    private JTable scheduleTable;

    public ScheduleView(ScheduleViewModel scheduleViewModel, RefreshController refreshController, NavigationController navigationController) {
        this.scheduleViewModel = scheduleViewModel;
        this.refreshController = refreshController;
        this.navigationController = navigationController;

        scheduleViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ScheduleViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        refresh = new JButton(ScheduleViewModel.REFRESH_BUTTON_LABEL);
        buttons.add(refresh);
        home = new JButton(ScheduleViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);

        scheduleTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(scheduleTable);

        setScheduleData(getScheduleData());

        refresh.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(refresh)){
                            refreshController.execute();
                        }
                    }
                }
        );

        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(home)){
                            navigationController.execute("logged in");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setScheduleData(getScheduleData());
    }

    private void setScheduleData(String[][] data){
        String[] columnNames = {"Home Team", "HomeScore", "AwayScore", "Away Team", "Date"};
        scheduleTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private String[][] getScheduleData() {
        ScheduleState currentState = scheduleViewModel.getState();
        return currentState.getData();
    }
}
