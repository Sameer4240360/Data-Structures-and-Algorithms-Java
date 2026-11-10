package stationeryinventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Stationery inventory kept in a singly linked list and saved in a MySQL table "items".
 * Every change is written to the database first, and the list is only changed
 * if the database operation succeeded, so the list and the table never disagree.
 */
class Functions {
    Stat hd;

    public Functions() {
        hd = null;
    }

    // ---------------------------------------------------------------- helpers

    private Stat findById(int id) {
        Stat t = hd;
        while (t != null) {
            if (t.id == id) return t;
            t = t.nxt;
        }
        return null;
    }

    private boolean validItem(String n, double p, int q) {
        if (n == null || n.trim().isEmpty()) {
            System.out.println("Item name cannot be empty:");
            return false;
        }
        if (p < 0 || q < 0) {
            System.out.println("Price and quantity cannot be negative:");
            return false;
        }
        return true;
    }

    private boolean insertDB(int id, String n, double p, int q) {
        String sql = "INSERT INTO items (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, n);
            ps.setDouble(3, p);
            ps.setInt(4, q);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
            return false;
        }
    }

    private boolean deleteDB(int id) {
        String sql = "DELETE FROM items WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("DB Error:" + e.getMessage());
            return false;
        }
    }

    // ------------------------------------------------------------- operations

    /** Loads all saved items from the database into the linked list. */
    public void loadFromDB() {
        hd = null;
        String sql = "SELECT * FROM items";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Stat tail = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String n = rs.getString("name");
                double p = rs.getDouble("price");
                int q = rs.getInt("quantity");

                Stat nS = new Stat(id, n, p, q);
                if (hd == null) {
                    hd = nS;
                } else {
                    tail.nxt = nS;
                }
                tail = nS;
            }
            System.out.println("Previous Data Loaded Successfully:");
        } catch (SQLException e) {
            System.out.println("Load Error:" + e.getMessage());
        }
    }

    public void addF(int id, String n, double p, int q) {
        if (!validItem(n, p, q)) return;
        if (findById(id) != null) {
            System.out.println("An item with this ID already exists:");
            return;
        }
        if (!insertDB(id, n, p, q)) return;

        Stat nS = new Stat(id, n, p, q);
        nS.nxt = hd;
        hd = nS;
        System.out.println("Item Added at Front & Saved to Database:");
    }

    public void addE(int id, String n, double p, int q) {
        if (!validItem(n, p, q)) return;
        if (findById(id) != null) {
            System.out.println("An item with this ID already exists:");
            return;
        }
        if (!insertDB(id, n, p, q)) return;

        Stat nS = new Stat(id, n, p, q);
        if (hd == null) {
            hd = nS;
        } else {
            Stat t = hd;
            while (t.nxt != null) {
                t = t.nxt;
            }
            t.nxt = nS;
        }
        System.out.println("Item Added at End & Saved to Database:");
    }

    /** Inserts a new item after the item whose ID is aId. */
    public void ins(int aId, int id, String n, double p, int q) {
        Stat t = findById(aId);
        if (t == null) {
            System.out.println("Target ID not found:");
            return;
        }
        if (!validItem(n, p, q)) return;
        if (findById(id) != null) {
            System.out.println("An item with this ID already exists:");
            return;
        }
        if (!insertDB(id, n, p, q)) return;

        Stat nS = new Stat(id, n, p, q);
        nS.nxt = t.nxt;
        t.nxt = nS;
        System.out.println("Item Inserted & Saved to Database:");
    }

    public void delF() {
        if (hd == null) {
            System.out.println("List is Empty:");
            return;
        }
        if (!deleteDB(hd.id)) return;

        System.out.println("Deleted from Front:" + hd.n);
        hd = hd.nxt;
    }

    public void delE() {
        if (hd == null) {
            System.out.println("List is Empty:");
            return;
        }
        if (hd.nxt == null) {
            if (!deleteDB(hd.id)) return;
            System.out.println("Deleted from End: " + hd.n);
            hd = null;
            return;
        }
        Stat t = hd;
        while (t.nxt.nxt != null) {
            t = t.nxt;
        }
        if (!deleteDB(t.nxt.id)) return;
        System.out.println("Deleted from End:" + t.nxt.n);
        t.nxt = null;
    }

    public void upd(int id, double nP, int nQ) {
        Stat t = findById(id);
        if (t == null) {
            System.out.println("Item not found:");
            return;
        }
        if (nP < 0 || nQ < 0) {
            System.out.println("Price and quantity cannot be negative:");
            return;
        }

        String sql = "UPDATE items SET price=?, quantity=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, nP);
            ps.setInt(2, nQ);
            ps.setInt(3, id);
            ps.executeUpdate();
            t.p = nP;
            t.q = nQ;
            System.out.println("Item Updated in List & Database:");
        } catch (SQLException e) {
            System.out.println("DB Error:" + e.getMessage());
        }
    }

    public void disp() {
        if (hd == null) {
            System.out.println("Store/Cart is Empty:");
            return;
        }
        System.out.println("Current Items in List:");
        Stat t = hd;
        while (t != null) {
            System.out.println(t);
            t = t.nxt;
        }
    }

    public void calcTot() {
        if (hd == null) {
            System.out.println("List is empty:Total: Rs 0.0");
            return;
        }
        double tA = 0;
        Stat t = hd;
        while (t != null) {
            tA += (t.p * t.q);
            t = t.nxt;
        }
        System.out.println("GRAND TOTAL:Rs" + tA);
    }
}
