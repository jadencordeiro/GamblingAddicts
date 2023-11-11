package view;

import schedule.service.refresh.interface_adapter.RefreshController;
import schedule.service.refresh.interface_adapter.ScheduleState;
import schedule.service.refresh.interface_adapter.ScheduleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScheduleView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "schedule";
    private final ScheduleViewModel scheduleViewModel;
    private final RefreshController refreshController;
    private final JButton refresh;

    public ScheduleView(ScheduleViewModel scheduleViewModel, RefreshController refreshController) {
        this.scheduleViewModel = scheduleViewModel;
        this.refreshController = refreshController;

        scheduleViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ScheduleViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        refresh = new JButton(ScheduleViewModel.REFRESH_BUTTON_LABEL);
        buttons.add(refresh);

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
    }

    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ScheduleState state = (ScheduleState) evt.getNewValue();
    }
}
