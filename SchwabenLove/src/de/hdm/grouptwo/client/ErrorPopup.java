package de.hdm.grouptwo.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * <code>ErrorPopup</code> is an extension of <code>DialogBox</code> and used to
 * inform the user of invalid inputs. <br>
 * This class is used instead of <code>Window.alert()</code> because alert()
 * sometimes causes Firefox to hang.<br>
 * 
 * @see <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=1180005">
 *      https://bugzilla.mozilla.org/show_bug.cgi?id=1180005</a>
 */
public class ErrorPopup extends DialogBox {
	private HTML errorHTML = new HTML();

	public ErrorPopup() {
		setText("Fehler");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		setModal(false);

		VerticalPanel vPanel = new VerticalPanel();
		LayoutPanel lPanel = new LayoutPanel();

		Button okButton = new Button("OK");
		okButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ErrorPopup.this.hide();
			}
		});

		lPanel.add(okButton);

		lPanel.setWidgetBottomHeight(okButton, 8, Unit.PX, 32, Unit.PX);
		lPanel.setWidgetRightWidth(okButton, 8, Unit.PX, 41, Unit.PX);

		vPanel.add(errorHTML);
		vPanel.add(lPanel);

		lPanel.setSize("100%", "48px");

		setWidget(vPanel);
	}

	/**
	 * Set an error message and show the popup.
	 * 
	 * @param error
	 *            The message to show
	 */
	public void showError(String error) {
		errorHTML.setHTML(error);
		center();
	}
}