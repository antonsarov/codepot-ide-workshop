/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonsarov.codepot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.HtmlBrowser;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "com.antonsarov.codepot.SOSearcher"
)
@ActionRegistration(
        iconBase = "com/antonsarov/codepot/so16.png",
        displayName = "#CTL_SOSearcher"
)
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0),
    @ActionReference(path = "Shortcuts", name = "OS-S")
})
@Messages("CTL_SOSearcher=Search on Stack Overflow")
public final class SOSearcher implements ActionListener {

    private final String SO_SEARCH_URL = "http://stackoverflow.com/search?q=";

    @Override
    public void actionPerformed(ActionEvent e) {
        NotifyDescriptor.InputLine notifyDescriptor = new NotifyDescriptor.InputLine("Search posts containing", "Search on Stack Overflow");
        DialogDisplayer.getDefault().notify(notifyDescriptor);
        String searchText = notifyDescriptor.getInputText();
        if (searchText == null || searchText.trim().isEmpty()) {
            NotificationDisplayer.getDefault().notify("SO Searcher",
                    ImageUtilities.loadImageIcon("com/antonsarov/codepot/so16.png", false),
                    "Not really sure what you want, huh?",
                    (ActionEvent e1) -> {});
        } else {
            String url;
            try {
                url = SO_SEARCH_URL + URLEncoder.encode(searchText, "UTF-8");
                HtmlBrowser.URLDisplayer.getDefault().showURL(new URL(url));
            } catch (UnsupportedEncodingException | MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
