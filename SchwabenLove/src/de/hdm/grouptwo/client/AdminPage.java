package de.hdm.grouptwo.client;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.grouptwo.shared.bo.Block;
import de.hdm.grouptwo.shared.bo.BookmarkList;
import de.hdm.grouptwo.shared.bo.Profile;

public class AdminPage extends ContentPage {
	private SimpleLayoutPanel sPanel = new SimpleLayoutPanel();
	private ScrolledTabLayoutPanel tabPanel = new ScrolledTabLayoutPanel(47,
			Unit.PX, Resources.INSTANCE.arrowLeft(),
			Resources.INSTANCE.arrowRight());

	public AdminPage() {
		super("Admin");
		initWidget(sPanel);
		// hPanel.setStyleName("admin-panel");

		// SelectionHandler to redraw DataGrid when it is shown
		tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {
				Widget w = tabPanel.getWidget(event.getSelectedItem());
				if (w instanceof DataGrid<?>) {
					DataGrid<?> dg = (DataGrid<?>) w;
					dg.redraw();
				}
			}
		});
	}

	@Override
	public void updatePage() {
		sPanel.clear();
		tabPanel.clear();

		administrationService
				.loadTableNames(new AsyncCallback<ArrayList<String>>() {
					public void onFailure(Throwable caught) {
						System.out.println("An error has occured");
					}

					public void onSuccess(ArrayList<String> result) {
						for (String tableName : result) {
							createTabs(tableName);
						}
					}
				});

		sPanel.add(tabPanel);
	}

	private void createTabs(String nfTableName) {
		final String tableName = nfTableName;

		switch (tableName) {
		case "block":
			administrationService
					.getAllBlocks(new AsyncCallback<ArrayList<Block>>() {
						public void onFailure(Throwable caught) {
							System.out.println("An error has occured");
						}

						public void onSuccess(ArrayList<Block> result) {
							tabPanel.add(createBlockTable(result), tableName);
						}
					});
			break;
		case "bookmark_list":
			administrationService
					.getAllBookmarkLists(new AsyncCallback<ArrayList<BookmarkList>>() {
						public void onFailure(Throwable caught) {
							System.out.println("An error has occured");
						}

						public void onSuccess(ArrayList<BookmarkList> result) {
							tabPanel.add(createBookmarkListTable(result),
									tableName);
						}
					});
			break;
		case "bookmark":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "description":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "information":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "profile":
			administrationService
					.getAllProfiles(new AsyncCallback<ArrayList<Profile>>() {
						public void onFailure(Throwable caught) {
							System.out.println("An error has occured");
						}

						public void onSuccess(ArrayList<Profile> result) {
							tabPanel.add(createProfileTable(result), tableName);
						}
					});
			break;
		case "property":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "search_profile":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "selection_item":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "selection":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "similarity_degree":
			tabPanel.add(new Label("..."), tableName);
			break;
		case "visit":
			tabPanel.add(new Label("..."), tableName);
			break;
		default:
			tabPanel.add(new Label("Table [" + tableName
					+ "] not implemented yet."), "*" + tableName);
		}
	}

	/**
	 * TODO: Use a data model factory instead?
	 * 
	 * @see AutoBeans
	 * 
	 * @param blocks
	 * @return
	 */
	private DataGrid<Block> createBlockTable(ArrayList<Block> blocks) {
		DataGrid<Block> table = new DataGrid<Block>();

		table.addColumn(new TextColumn<Block>() {
			public String getValue(Block object) {
				return Integer.toString(object.getId());
			}
		}, "block_id");
		table.addColumn(new TextColumn<Block>() {
			public String getValue(Block object) {
				return Integer.toString(object.getBlockerProfileId());
			}
		}, "fk_blocker_profile_id");
		table.addColumn(new TextColumn<Block>() {
			public String getValue(Block object) {
				return Integer.toString(object.getBlockedProfileId());
			}
		}, "fk_blocked_profile_id");

		table.setRowCount(blocks.size());
		table.setRowData(blocks);

		return table;
	}

	private DataGrid<BookmarkList> createBookmarkListTable(
			ArrayList<BookmarkList> bookmarkLists) {
		DataGrid<BookmarkList> table = new DataGrid<BookmarkList>();

		table.addColumn(new TextColumn<BookmarkList>() {
			public String getValue(BookmarkList object) {
				return Integer.toString(object.getId());
			}
		}, "bookmark_list_id");
		table.addColumn(new TextColumn<BookmarkList>() {
			public String getValue(BookmarkList object) {
				return Integer.toString(object.getProfileId());
			}
		}, "fk_profile_id");

		table.setRowCount(bookmarkLists.size());
		table.setRowData(bookmarkLists);

		return table;
	}

	private DataGrid<Profile> createProfileTable(ArrayList<Profile> profiles) {
		DataGrid<Profile> table = new DataGrid<Profile>();

		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return Integer.toString(object.getId());
			}
		}, "profile_id");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getFirstName();
			}
		}, "first_name");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getLastName();
			}
		}, "last_name");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getGender();
			}
		}, "gender");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getBirthdate().toString();
			}
		}, "birtdate");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return Integer.toString(object.getAge());
			}
		}, "age");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getLocation();
			}
		}, "location");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return Integer.toString(object.getHeight());
			}
		}, "height");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getPhysique();
			}
		}, "physique");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getHairColor();
			}
		}, "hair_color");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getPhysique();
			}
		}, "smoker");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getSmoker();
			}
		}, "education");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getEducation();
			}
		}, "physique");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getProfession();
			}
		}, "profession");
		table.addColumn(new TextColumn<Profile>() {
			public String getValue(Profile object) {
				return object.getReligion();
			}
		}, "religion");

		table.setRowCount(profiles.size());
		table.setRowData(profiles);

		return table;
	}
}
