package de.hdm.grouptwo.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImprintPage extends ContentPage {
	SimpleLayoutPanel sPanel = new SimpleLayoutPanel();

	public ImprintPage() {
		super("Impressum");
		initWidget(sPanel);
		sPanel.add(new Label(name));
	}

	@Override
	public void updatePage() {
		VerticalPanel vPanel = new VerticalPanel();						
		
		HTML html1 = new HTML();
		HTML html2 = new HTML();
		HTML html3 = new HTML();
		HTML html4 = new HTML();
		HTML html5 = new HTML();
		HTML html6 = new HTML();
		HTML html7 = new HTML();
		HTML html8 = new HTML();
		HTML html9 = new HTML();
		
		
					
		html1.setHTML("<div class='impressum'><h1>Impressum</h1><p>Angaben gem\u00e4\u00df § 5 TMG</p><p> "
				+ "Hochschule der Medien<br> "
				+ "Nobelstra\u00dfe 8 <br>"
				+ "70569 Stuttgart <br> "
				+ "</p><p> <strong>Vertreten durch: </strong><br>"
				+ "Jerome Badt<br>"
				+ "Joshua Hill<br>"
				+ "Denis Thierry<br>"
				+ "Manuel Russ<br>"
				+ "Duc Nguyen<br>"
				+ "J\u00f6rg Jarmer<br>"				
				+ "</p><p><strong>Kontakt:</strong> <br>"
				+ "E-Mail: <a href='mailto:max@muster.de'>jb@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:max@muster.de'>jh@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:max@muster.de'>dt@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:max@muster.de'>mn@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:max@muster.de'>dn@hdm-stuttgart.de</a></br>"
				+ "E-Mail: <a href='mailto:max@muster.de'>js@hdm-stuttgart.de</a></br></p><p>");
				
		html2.setHTML("<p><strong>Haftungsausschluss: </strong><br><br><strong>Haftung f\u00fcr Inhalte</strong><br><br>"
				+ "Die Inhalte unserer Seiten wurden mit gr\u00f6\u00dfter Sorgfalt erstellt. F\u00fcr die Richtigkeit, "
				+ "Vollst\u00e4ndigkeit und Aktualit\u00e4t der Inhalte k\u00f6nnen wir jedoch keine Gew\u00e4hr \u00fcbernehmen. "
				+ "Als Diensteanbieter sind wir gem\u00e4\u00df § 7 Abs.1 TMG f\u00fcr eigene Inhalte auf diesen Seiten "
				+ "nach den allgemeinen Gesetzen verantwortlich. Nach §§ 8 bis 10 TMG sind wir als "
				+ "Diensteanbieter jedoch nicht verpflichtet, \u00fcbermittelte oder gespeicherte fremde "
				+ "Informationen zu \u00fcberwachen oder nach Umst\u00e4nden zu forschen, die auf eine rechtswidrige"
				+ " T\u00e4tigkeit hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von "
				+ "Informationen nach den allgemeinen Gesetzen bleiben hiervon unber\u00fchrt. Eine "
				+ "diesbez\u00fcgliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten"
				+ " Rechtsverletzung m\u00f6glich. Bei Bekanntwerden von entsprechenden Rechtsverletzungen"
				+ " werden wir diese Inhalte umgehend entfernen.<br>");
		
		html3.setHTML("<br><strong>Haftung f\u00fcr Links</strong><br><br>"
				+ "Unser Angebot enth\u00e4lt Links zu externen Webseiten Dritter, auf deren Inhalte wir "
				+ "keinen Einfluss haben. Deshalb k\u00f6nnen wir f\u00fcr diese fremden Inhalte auch keine Gew\u00e4hr"
				+ " \u00fcbernehmen. F\u00fcr die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter "
				+ "oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt"
				+ " der Verlinkung auf m\u00f6gliche Rechtsverst\u00f6\u00dfe \u00fcberpr\u00fcft. Rechtswidrige Inhalte waren"
				+ " zum Zeitpunkt der Verlinkung nicht erkennbar. Eine permanente inhaltliche Kontrolle"
				+ " der verlinkten Seiten ist jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung"
				+ " nicht zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links"
				+ " umgehend entfernen.<br> ");

		html4.setHTML("<br><strong>Urheberrecht</strong><br><br>"
				+ "Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten "
				+ "unterliegen dem deutschen Urheberrecht. Die Vervielf\u00e4ltigung, Bearbeitung, Verbreitung"
				+ " und jede Art der Verwertung au\u00dferhalb der Grenzen des Urheberrechtes bed\u00fcrfen der "
				+ "schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers. Downloads und Kopien"
				+ " dieser Seite sind nur f\u00fcr den privaten, nicht kommerziellen Gebrauch gestattet. "
				+ "Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt wurden, werden die "
				+ "Urheberrechte Dritter beachtet. Insbesondere werden Inhalte Dritter als solche "
				+ "gekennzeichnet.Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam "
				+ "werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von "
				+ "Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen.<br>");

		html5.setHTML("<br><strong>Datenschutz</strong><br><br>"
				+ "	Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener Daten"
				+ " m\u00f6glich. Soweit auf unseren Seiten personenbezogene Daten (beispielsweise Name, "
				+ "Anschrift oder eMail-Adressen)erhoben werden, erfolgt dies, soweit m\u00f6glich, stets "
				+ "auf freiwilliger Basis. Diese Daten werden ohne Ihre ausdr\u00fcckliche Zustimmung nicht"
				+ " an Dritte weitergegeben. <br>"
				+ " Wir weisen darauf hin, dass die Daten\u00fcbertragung im Internet (z.B. bei der "
				+ "Kommunikation per E-Mail) Sicherheitsl\u00fccken aufweisen kann. Ein l\u00fcckenloser Schutz "
				+ "der Daten vor dem Zugriff durch Dritte ist nicht m\u00f6glich. <br>"
				+ "	Der Nutzung von im Rahmen der Impressumspflicht ver\u00f6ffentlichten Kontaktdaten "
				+ "durch Dritte zur \u00dcbersendung von nicht ausdr\u00fccklich angeforderter Werbung und "
				+ "Informationsmaterialien wird hiermit ausdr\u00fccklich widersprochen. Die Betreiber der"
				+ " Seiten behalten sich ausdr\u00fccklich rechtliche Schritte im Falle der unverlangten"
				+ " Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor.<br><br>");
		
		html6.setHTML("<br><strong>Google Analytics</strong><br><br>"
				+ "Diese Website benutzt Google Analytics, einen Webanalysedienst der Google Inc. "
				+ "(''Google''). Google Analytics verwendet sog. ''Cookies'', Textdateien, die auf "
				+ "Ihrem Computer gespeichert werden und die eine Analyse der Benutzung der Website"
				+ " durch Sie erm\u00f6glicht. Die durch den Cookie erzeugten Informationen \u00fcber Ihre"
				+ " Benutzung diese Website (einschlie\u00dflich Ihrer IP-Adresse) wird an einen Server"
				+ " von Google in den USA \u00fcbertragen und dort gespeichert. Google wird diese "
				+ "Informationen benutzen, um Ihre Nutzung der Website auszuwerten, um Reports \u00fcber "
				+ "die Websiteaktivit\u00e4ten f\u00fcr die Websitebetreiber zusammenzustellen und um weitere"
				+ " mit der Websitenutzung und der Internetnutzung verbundene Dienstleistungen "
				+ "zu erbringen. Auch wird Google diese Informationen gegebenenfalls an Dritte "
				+ "\u00fcbertragen, sofern dies gesetzlich vorgeschrieben oder soweit Dritte diese Daten "
				+ "im Auftrag von Google verarbeiten. Google wird in keinem Fall Ihre IP-Adresse mit "
				+ "anderen Daten der Google in Verbindung bringen. Sie k\u00f6nnen die Installation der "
				+ "Cookies durch eine entsprechende Einstellung Ihrer Browser Software verhindern; "
				+ "wir weisen Sie jedoch darauf hin, dass Sie in diesem Fall gegebenenfalls nicht "
				+ "s\u00e4mtliche Funktionen dieser Website voll umf\u00e4nglich nutzen k\u00f6nnen. Durch die "
				+ "Nutzung dieser Website erkl\u00e4ren Sie sich mit der Bearbeitung der \u00fcber Sie erhobenen"
				+ " Daten durch Google in der zuvor beschriebenen Art und Weise und zu dem zuvor"
				+ " benannten Zweck einverstanden.<br>");

		html7.setHTML("<br><strong>Google AdSense</strong><br><br>"
				+ "Diese Website benutzt Google Adsense, einen Webanzeigendienst der Google Inc., USA "
				+ "(''Google''). Google Adsense verwendet sog. ''Cookies'' (Textdateien), die auf "
				+ "Ihrem Computer gespeichert werden und die eine Analyse der Benutzung der Website"
				+ " durch Sie erm\u00f6glicht. Google Adsense verwendet auch sog. ''Web Beacons'' "
				+ "(kleine unsichtbare Grafiken) zur Sammlung von Informationen. Durch die Verwendung"
				+ " des Web Beacons k\u00f6nnen einfache Aktionen wie der Besucherverkehr auf der Webseite"
				+ " aufgezeichnet und gesammelt werden. Die durch den Cookie und/oder Web Beacon "
				+ "erzeugten Informationen \u00fcber Ihre Benutzung diese Website (einschlie\u00dflich"
				+ " Ihrer IP-Adresse) werden an einen Server von Google in den USA \u00fcbertragen "
				+ "und dort gespeichert. Google wird diese Informationen benutzen, um Ihre Nutzung "
				+ "der Website im Hinblick auf die Anzeigen auszuwerten, um Reports \u00fcber die "
				+ "Websiteaktivit\u00e4ten und Anzeigen f\u00fcr die Websitebetreiber zusammenzustellen"
				+ " und um weitere mit der Websitenutzung und der Internetnutzung verbundene "
				+ "Dienstleistungen zu erbringen. Auch wird Google diese Informationen gegebenenfalls "
				+ "an Dritte \u00fcbertragen, sofern dies gesetzlich vorgeschrieben oder soweit Dritte "
				+ "diese Daten im Auftrag von Google verarbeiten. Google wird in keinem Fall Ihre "
				+ "IP-Adresse mit anderen Daten der Google in Verbindung bringen. Das Speichern von "
				+ "Cookies auf Ihrer Festplatte und die Anzeige von Web Beacons k\u00f6nnen Sie verhindern, "
				+ "indem Sie in Ihren Browser-Einstellungen ''keine Cookies akzeptieren'' w\u00e4hlen "
				+ "(Im MS Internet-Explorer unter ''Extras > Internetoptionen > Datenschutz > "
				+ "Einstellung''; im Firefox unter ''Extras > Einstellungen > Datenschutz > Cookies'');"
				+ " wir weisen Sie jedoch darauf hin, dass Sie in diesem Fall gegebenenfalls "
				+ "nicht s\u00e4mtliche Funktionen dieser Website voll umf\u00e4nglich nutzen k\u00f6nnen. Durch "
				+ "die Nutzung dieser Website erkl\u00e4ren Sie sich mit der Bearbeitung der \u00fcber Sie "
				+ "erhobenen Daten durch Google in der zuvor beschriebenen Art und Weise und zu "
				+ "dem zuvor benannten Zweck einverstanden.</p><br> ");
				
			
		
		vPanel.add(html1);	
		vPanel.add(html2);
		vPanel.add(html3);
		vPanel.add(html4);
		vPanel.add(html5);
		vPanel.add(html6);
		vPanel.add(html7);
		vPanel.add(html8);
		//sPanel.add(vPanel);
		
		vPanel.setWidth("800px");
		//vPanel.setBorderWidth(1);
			
							
					
			RootPanel.get().add(vPanel);
		
		
	}
}
