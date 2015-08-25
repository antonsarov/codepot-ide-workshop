import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Created by izvanzemnoto on 22.8.2015 ?..
 */
public class SearchSO extends AnAction {

    private static final String SO_SEARCH_URL = "http://stackoverflow.com/search?q=";

    private static final String GROUP_DISPLAY_ID = "Stack Oveflow Searcher";
    private static final String NOTIFICATION_TITLE = "Empty search term";
    private static final String NOTIFICATION_CONTENT = "Not really sure what you want, huh?";

    public void actionPerformed(AnActionEvent e) {
        Icon icon = IconLoader.getIcon("/so16.png");
        String input = Messages.showInputDialog("Search for posts containing", "Search on SO", icon);
        if (input==null || input.trim().isEmpty()) {
            Notifications.Bus.notify(new Notification(GROUP_DISPLAY_ID, NOTIFICATION_TITLE, NOTIFICATION_CONTENT, NotificationType.WARNING));
        } else {
            String request = SO_SEARCH_URL + input;
            BrowserLauncher.getInstance().open(request);
        }
    }
}
