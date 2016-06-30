package de.hdm.grouptwo.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImprintPage extends ContentPage {
	private ScrollPanel sPanel = new ScrollPanel();

	public ImprintPage() {
		super("Impressum");
		initWidget(sPanel);

		initImprint();
	}

	@Override
	public void updatePage() {
	}

	/**
	 * Create VerticalPanel and HTML objects for the content.
	 */
	private void initImprint() {
		VerticalPanel vPanel = new VerticalPanel();
		// Set the width for the VerticalPanel
		vPanel.setWidth("90%");

		HTML html1 = new HTML();
		HTML html2 = new HTML();
		HTML html3 = new HTML();
		HTML html4 = new HTML();
		HTML html5 = new HTML();
		HTML html6 = new HTML();
		HTML html7 = new HTML();
		HTML html8 = new HTML();

		// Assign the imprint content to the html objects
		html1.setHTML("<div class='impressum'><h1>Impressum</h1>"
				+ "<p>Angaben gemäß § 5 TMG</p><p>"
				+ "Hochschule der Medien<br> "
				+ "Nobelstraße 8 <br>"
				+ "70569 Stuttgart <br></p><p>"
				+ "<strong>Vertreten durch:</strong><br>"
				+ "Jerome Badt<br>"
				+ "Denis Thierry<br>"
				+ "Joshua Hill<br>"
				+ "Jörg Jarmer<br>"
				+ "Manuel Russ<br>"
				+ "Duc Nguyen<br>"
				+ "</p><p><strong>Kontakt:</strong><br>"
				+ "E-Mail: <a href='mailto:jb163@hdm-stuttgart.de'>jb163@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:dt019@hdm-stuttgart.de'>dt019@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:jh147@hdm-stuttgart.de'>jh147@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:jj027@hdm-stuttgart.de'>jj027@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:mr134@hdm-stuttgart.de'>mr134@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:dn021@hdm-stuttgart.de'>dn021@hdm-stuttgart.de</a></br></p><p>");

		html2.setHTML("<p><strong>Haftungsausschluss:</strong><br><br><strong>Haftung für Inhalte</strong><br>"
				+ "Die Inhalte unserer Seiten wurden mit größter Sorgfalt erstellt. "
				+ "Für die Richtigkeit, Vollständigkeit und Aktualität der "
				+ "Inhalte können wir jedoch keine Gewähr übernehmen. Als "
				+ "Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene "
				+ "Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. "
				+ "Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, "
				+ "übermittelte oder gespeicherte fremde Informationen zu überwachen "
				+ "oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit "
				+ "hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von "
				+ "Informationen nach den allgemeinen Gesetzen bleiben hiervon unberührt. "
				+ "Eine diesbezügliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis "
				+ "einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von "
				+ "entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.<br><br>");

		html3.setHTML("<strong>Haftung für Links</strong><br>"
				+ "Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren "
				+ "Inhalte wir keinen Einfluss haben. Deshalb können wir für diese "
				+ "fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte "
				+ "der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der "
				+ "Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der "
				+ "Verlinkung auf mögliche Rechtsverstöße überprüft. "
				+ "Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar. "
				+ "Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist jedoch ohne "
				+ "konkrete Anhaltspunkte einer Rechtsverletzung nicht zumutbar. Bei "
				+ "Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend "
				+ "entfernen.<br><br>");

		html4.setHTML("<strong>Urheberrecht</strong><br>"
				+ "Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten "
				+ "unterliegen dem deutschen Urheberrecht. Die Vervielfältigung, Bearbeitung, Verbreitung "
				+ "und jede Art der Verwertung außerhalb der Grenzen des Urheberrechtes "
				+ "bedürfen der schriftlichen Zustimmung des jeweiligen Autors bzw. "
				+ "Erstellers. Downloads und Kopien dieser Seite sind nur für den privaten, "
				+ "nicht kommerziellen Gebrauch gestattet. Soweit die Inhalte auf dieser "
				+ "Seite nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter "
				+ "beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. "
				+ "Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam werden, "
				+ "bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von "
				+ "Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen.<br><br>");

		html5.setHTML("<strong>Datenschutz</strong><br>"
				+ "Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener "
				+ "Daten möglich. Soweit auf unseren Seiten personenbezogene Daten "
				+ "(beispielsweise Name, Anschrift oder eMail-Adressen) erhoben werden, erfolgt "
				+ "dies, soweit möglich, stets auf freiwilliger Basis. Diese Daten werden "
				+ "ohne Ihre ausdrückliche Zustimmung nicht an Dritte weitergegeben.<br>"
				+ "Wir weisen darauf hin, dass die Datenübertragung im Internet "
				+ "(z.B. bei der Kommunikation per E-Mail) Sicherheitslücken aufweisen kann. "
				+ "Ein lückenloser Schutz der Daten vor dem Zugriff durch Dritte ist "
				+ "nicht möglich.<br>"
				+ "Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten "
				+ "Kontaktdaten durch Dritte zur Übersendung von nicht ausdrücklich "
				+ "angeforderter Werbung und Informationsmaterialien wird hiermit "
				+ "ausdrücklich widersprochen. Die Betreiber der Seiten behalten sich "
				+ "ausdrücklich rechtliche Schritte im Falle der unverlangten "
				+ "Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor.<br><br>");

		html6.setHTML("<strong>Google Analytics</strong><br>"
				+ "Diese Website benutzt Google Analytics, einen Webanalysedienst der Google "
				+ "Inc. (''Google''). Google Analytics verwendet sog. ''Cookies'', Textdateien, "
				+ "die auf Ihrem Computer gespeichert werden und die eine Analyse der Benutzung "
				+ "der Website durch Sie ermöglicht. Die durch den Cookie erzeugten "
				+ "Informationen über Ihre Benutzung diese Website (einschließlich "
				+ "Ihrer IP-Adresse) wird an einen Server von Google in den USA übertragen "
				+ "und dort gespeichert. Google wird diese Informationen benutzen, um Ihre "
				+ "Nutzung der Website auszuwerten, um Reports über die "
				+ "Websiteaktivitäten für die Websitebetreiber zusammenzustellen und "
				+ "um weitere mit der Websitenutzung und der Internetnutzung verbundene "
				+ "Dienstleistungen zu erbringen. Auch wird Google diese Informationen "
				+ "gegebenenfalls an Dritte übertragen, sofern dies gesetzlich "
				+ "vorgeschrieben oder soweit Dritte diese Daten im Auftrag von Google "
				+ "verarbeiten. Google wird in keinem Fall Ihre IP-Adresse mit anderen Daten "
				+ "der Google in Verbindung bringen. Sie können die Installation der "
				+ "Cookies durch eine entsprechende Einstellung Ihrer Browser Software verhindern; "
				+ "wir weisen Sie jedoch darauf hin, dass Sie in diesem Fall gegebenenfalls nicht "
				+ "sämtliche Funktionen dieser Website voll umfänglich nutzen "
				+ "können. Durch die Nutzung dieser Website erklären Sie sich mit "
				+ "der Bearbeitung der über Sie erhobenen Daten durch Google in der zuvor "
				+ "beschriebenen Art und Weise und zu dem zuvor benannten Zweck einverstanden.<br><br>");

		html7.setHTML("<strong>Google AdSense</strong><br>"
				+ "Diese Website benutzt Google Adsense, einen Webanzeigendienst der Google Inc., "
				+ "USA (''Google''). Google Adsense verwendet sog. ''Cookies'' (Textdateien), "
				+ "die auf Ihrem Computer gespeichert werden und die eine Analyse der Benutzung "
				+ "der Website durch Sie ermöglicht. Google Adsense verwendet auch sog. "
				+ "''Web Beacons'' (kleine unsichtbare Grafiken) zur Sammlung von Informationen. "
				+ "Durch die Verwendung des Web Beacons können einfache Aktionen wie der "
				+ "Besucherverkehr auf der Webseite aufgezeichnet und gesammelt werden. Die "
				+ "durch den Cookie und/oder Web Beacon erzeugten Informationen über Ihre "
				+ "Benutzung diese Website (einschließlich Ihrer IP-Adresse) werden an einen "
				+ "Server von Google in den USA übertragen und dort gespeichert. Google "
				+ "wird diese Informationen benutzen, um Ihre Nutzung der Website im Hinblick "
				+ "auf die Anzeigen auszuwerten, um Reports über die Websiteaktivitäten "
				+ "und Anzeigen für die Websitebetreiber zusammenzustellen und um weitere "
				+ "mit der Websitenutzung und der Internetnutzung verbundene Dienstleistungen "
				+ "zu erbringen. Auch wird Google diese Informationen gegebenenfalls an Dritte "
				+ "übertragen, sofern dies gesetzlich vorgeschrieben oder soweit Dritte "
				+ "diese Daten im Auftrag von Google verarbeiten. Google wird in keinem Fall Ihre "
				+ "IP-Adresse mit anderen Daten der Google in Verbindung bringen. Das Speichern von "
				+ "Cookies auf Ihrer Festplatte und die Anzeige von Web Beacons können Sie "
				+ "verhindern, indem Sie in Ihren Browser-Einstellungen ''keine Cookies "
				+ "akzeptieren'' wählen (Im MS Internet-Explorer unter ''Extras > "
				+ "Internetoptionen > Datenschutz > Einstellung''; im Firefox unter ''Extras > "
				+ "Einstellungen > Datenschutz > Cookies''); wir weisen Sie jedoch darauf hin, dass "
				+ "Sie in diesem Fall gegebenenfalls nicht sämtliche Funktionen dieser Website "
				+ "voll umfänglich nutzen können. Durch die Nutzung dieser Website "
				+ "erklären Sie sich mit der Bearbeitung der über Sie erhobenen Daten "
				+ "durch Google in der zuvor beschriebenen Art und Weise und zu dem zuvor benannten "
				+ "Zweck einverstanden.</p>");

		// Add all html objects to the VerticalPanel and the VerticalPanel to
		// the ScrollPanel
		vPanel.add(html1);
		vPanel.add(html2);
		vPanel.add(html3);
		vPanel.add(html4);
		vPanel.add(html5);
		vPanel.add(html6);
		vPanel.add(html7);
		vPanel.add(html8);
		sPanel.add(vPanel);
	}
}
