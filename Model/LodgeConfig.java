package lodgeman.lalitman.model.business.LodgeConfig;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lodgeman.lalitman.model.pojo.Addon;
import lodgeman.lalitman.model.pojo.Amenityroom;
import lodgeman.lalitman.model.pojo.Askadvance;
import lodgeman.lalitman.model.pojo.Bills;
import lodgeman.lalitman.model.pojo.Bookedroomamenities;
import lodgeman.lalitman.model.pojo.Bookingaddon;
import lodgeman.lalitman.model.pojo.Bookings;
import lodgeman.lalitman.model.pojo.Creditbills;
import lodgeman.lalitman.model.pojo.Creditbilltrans;
import lodgeman.lalitman.model.pojo.Creditowners;
import lodgeman.lalitman.model.pojo.Daysession;
import lodgeman.lalitman.model.pojo.Daytariff;
import lodgeman.lalitman.model.pojo.Employees;
import lodgeman.lalitman.model.pojo.EntityEnum;
import lodgeman.lalitman.model.pojo.Groups;
import lodgeman.lalitman.model.pojo.Guests;
import lodgeman.lalitman.model.pojo.Identityprooftype;
import lodgeman.lalitman.model.pojo.Itemdirty;
import lodgeman.lalitman.model.pojo.Iteminlaundry;
import lodgeman.lalitman.model.pojo.Iteminstore;
import lodgeman.lalitman.model.pojo.Iteminuse;
import lodgeman.lalitman.model.pojo.Itemmastertrans;
import lodgeman.lalitman.model.pojo.Items;
import lodgeman.lalitman.model.pojo.Itemtrans;
import lodgeman.lalitman.model.pojo.Jobtitles;
import lodgeman.lalitman.model.pojo.Ledgers;
import lodgeman.lalitman.model.pojo.Menuitems;
import lodgeman.lalitman.model.pojo.Menutype;
import lodgeman.lalitman.model.pojo.Payments;
import lodgeman.lalitman.model.pojo.Properties;
import lodgeman.lalitman.model.pojo.QueryManager;
import lodgeman.lalitman.model.pojo.Receipts;
import lodgeman.lalitman.model.pojo.Receives;
import lodgeman.lalitman.model.pojo.Reservationwadvance;
import lodgeman.lalitman.model.pojo.Reservationwoadvance;
import lodgeman.lalitman.model.pojo.Room;
import lodgeman.lalitman.model.pojo.Roomcleaners;
import lodgeman.lalitman.model.pojo.Roomtype;
import lodgeman.lalitman.model.pojo.Sessiondetails;
import lodgeman.lalitman.model.pojo.Totaltariff;
import lodgeman.lalitman.model.pojo.Userlog;
import lodgeman.lalitman.model.pojo.Users;

public class LodgeConfig 
{     

    
    public boolean newIdentityProofType(String idTypeName, Integer identityidlength )
    {
        Identityprooftype identityprooftype = new Identityprooftype(new  Integer(0), idTypeName, identityidlength);
        QueryManager qm = QueryManager.getInstance();
        
        qm.Save(EntityEnum.IDENTITYPROOFTYPE, identityprooftype);
        System.out.println("Saved ID Type");
        return false;
    }
    
    public boolean newEmployees(String name, String lastname, Integer contactno, String address, String city, String state, Identityprooftype idprooftype,byte[] image, Integer idproofno)
    {
        Employees employees = new Employees(idproofno, name, lastname, contactno, address, city, state, null, idproofno);
        employees.setIdprooftypeid(idprooftype);        
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.EMPLOYEES, employees);
        System.out.println("Saved Employees");
        
        return false;
    }
    
    public boolean newJobTitle(String jobTitle, String description)
    {
          Jobtitles jobtitles = new Jobtitles(new Integer(0), jobTitle, description);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.JOBTITLES, jobtitles);
        System.out.println("Saved Employees");
        return false;        
    }
    
    public boolean newUser(String username, String password, Employees employId, Jobtitles userRoleId)
    {
        Users users = new Users(Integer.SIZE, username, password, false);
        users.setEmployid(employId);
        users.setUserroleid(userRoleId);
        
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.USERS, users);
        
        System.out.println("User saved.");
        return false;
    }
    
    public boolean newProperties(String name, String address, Integer contact1, Integer contact2, byte[] images, Integer noOfFloor)
    {
        Properties properties = new Properties(new Integer(0), name, address, contact1, contact2, images, noOfFloor);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.PROPERTIES, properties);
        
        System.out.println("Properties saved.");
        return false;
    }
    
    public static boolean newRoomType(String name, String description, Integer maxAdult, Integer maxChildren, BigDecimal rate)
    {
        Roomtype roomtype = new Roomtype(new Integer(0), name, description, maxAdult, maxChildren,rate);
        
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ROOMTYPE, roomtype);
        System.out.println("Room type saved.");
        return true;
    }
    
    public static boolean newRoom(Integer roomno,Integer noOfFloor, Roomtype roomtype)
    {
        Room room = new Room(roomno, noOfFloor);
        room.setRoomtypeid(roomtype);
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ROOM, room);
        System.out.println("Room saved.");
        return true;
    }
    
    public boolean newMenuType(String name, String description)
    {
        Menutype menutype = new Menutype(new Integer(0), name, description);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.MENUTYPE, menutype);
        
        System.out.println("Menutype saved.");
        return false;
    }
    
    public boolean newMenuitem(String itemname, Menutype menutype, Integer rate, boolean isVeg,Integer available)
    {
        Menuitems menuitem = new Menuitems(new Integer(0), itemname, rate, true, available);
        menuitem.setMenutypeid(menutype);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.MENUITEMS, menuitem);
        
        System.out.println("Menuitem saved.");
        return false;
    }
    
    public boolean newBookings(Date startDate, Date fromTo, String status, Integer noOfPersons, Users users, Guests guests, Room room,Integer regno)
    {
        Bookings bookings = new Bookings(new Integer(0), status, noOfPersons);
        bookings.setCustomerid(guests);
        bookings.setRoomno(room);
        bookings.setUserid(users);
        bookings.setRegno(regno);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.BOOKINGS, bookings);
        
        System.out.println("Booking saved.");
        return false;
    }
    
    public static boolean newReceipts(String guestname,String roomno, Date ardate,Integer noofpeople, BigDecimal roomrent, BigDecimal luxuryrate, Integer advancepaid, String inwords, Bookings bookings,Integer regno, Boolean extra)
    {
        Receipts receipts = new Receipts(new Integer(0), guestname, roomno, ardate, noofpeople, roomrent, luxuryrate.intValue(), advancepaid, inwords);
        receipts.setBookingid(bookings);
        receipts.setRegno(regno);
        receipts.setExtra(extra);
        
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.RECEIPTS, receipts);
        System.out.println("Receipts saved.");
        
        return true;
    }
    
    public static boolean newBills(int roomno, Date billdate, String guestname, Date checkindate, Date checkoutdate, int noofpersons, String advreceiptsno, int noofdays, BigDecimal rentperday, int extrabed, int extraperson, BigDecimal otherservices, BigDecimal additionalluxurytax, int totaladvance, int cashreceived, BigDecimal cashrefund, Bookings bookings, BigDecimal totalbill, Integer regno)
    {
       Bills bill = new Bills(new Integer(0),  roomno,  billdate,  guestname,  checkindate, checkoutdate, noofpersons, advreceiptsno, noofdays, rentperday, extrabed, extraperson, otherservices,  additionalluxurytax, totaladvance,  cashreceived,cashrefund, totalbill);
       bill.setBookingid(bookings);
       bill.setRegno(regno);
       
       QueryManager qm = QueryManager.getInstance();
       qm.Save(EntityEnum.BILLS, bill);
       System.out.println("Bills saved.");
       
       return true;
    }
    
    public static boolean newBookingAddon(Date fromDate, Date toDate, Bookings bookings, Room room, Addon addon, Integer qty)
    {
       Bookingaddon bookingaddon = new Bookingaddon(Integer.SIZE, fromDate, qty);
       bookingaddon.setAddontype(addon);
       bookingaddon.setBookingid(bookings);
       bookingaddon.setRoomno(room);
       bookingaddon.setTodate(toDate);
       
       
       QueryManager qm = QueryManager.getInstance();
       qm.Save(EntityEnum.BOOKINGADDON, bookingaddon);
       System.out.println("Booking addon saved.");
       
       return true;
    }
    
    public static  boolean newUserlog(Date startdate, Users usersid, Integer openingbalance, Daysession thissession)
    {
        Userlog userlog = new Userlog(Integer.SIZE);
        userlog.setStartdatetime(startdate);
        userlog.setUserid(usersid);
        userlog.setOpeningbalance(openingbalance);
        userlog.setDaysessionid(thissession);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.USERLOG, userlog);
        System.out.println("userlog saved.");
       
        return true;
    }
    
    public static boolean newLedger(String name, Integer openingBalance, String description, Groups groups)
    {
        Ledgers ledger = new Ledgers(Integer.SIZE, name, openingBalance, description);
        ledger.setGroupId(groups);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.LEDGERS, ledger);
        System.out.println("Ledgers saved.");
        return true;
    }
    
    public static boolean newPayments(Date paymentdate, Ledgers ledgers, String note, Users users, Integer amount)
    {
        Payments payments = new Payments(new Integer(0), paymentdate, amount);
        payments.setLedgertype(ledgers);
        payments.setNote(note);
        payments.setUserid(users);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.PAYMENTS, payments);
        System.out.println("payments saved.");
        return true;
    }
    
    public static boolean newReceives(Date receiveDate, Ledgers ledgers, String note, Users users, Integer amount)
    {
        Receives receives = new Receives(new Integer(0), receiveDate, amount);
        receives.setLedgertype(ledgers);
        receives.setNote(note);
        receives.setUserid(users);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.RECEIVES, receives);
        System.out.println("Receives saved.");
        return true;
    }
    
    public static boolean newSessiondetails(Integer openingbalance,Integer refundtotal,Integer advancetotal, Integer receivetotal, Integer expenseTotal, Integer closingbalance, Date currentDate, Users userid, Integer creditbill, Integer creditbillrecv, Integer cashincounter, Integer difference, Integer extraaddition)
    {
        //Sessiondetails sessiondetails = new Sessiondetails(new Integer(0), openingbalance, refundtotal, advancetotal, receivetotal, expenseTotal, closingbalance, currentDate);
        Sessiondetails sessiondetails = new Sessiondetails(refundtotal, openingbalance, refundtotal, advancetotal, receivetotal, expenseTotal, closingbalance, currentDate, creditbill, creditbillrecv, cashincounter, difference, extraaddition);
        sessiondetails.setUserid(userid);
        
        QueryManager qm =  QueryManager.getInstance();
        qm.Save(EntityEnum.SESSIONDETAILS, sessiondetails);
        System.out.println("Session saved.");
        return true;
    }
    
    public static boolean newCreditowner(String name, String desc)
    {
        Creditowners creditowners = new Creditowners(Integer.SIZE, name, desc);
        
        QueryManager qm =  QueryManager.getInstance();
        qm.Save(EntityEnum.CREDITOWNERS, creditowners);
        System.out.println("Credit owners saved.");
        return true;
    }
    
    public static boolean newCreditbills(Date entrydate, Boolean paid, Date cleardate, Creditowners creditownerid, Bills billno)
    {
        Creditbills creditbills = new Creditbills(Integer.SIZE, entrydate, paid);
        creditbills.setBillno(billno);
        creditbills.setCreditownerid(creditownerid);
        creditbills.setCleardate(cleardate);
        
        QueryManager qm =  QueryManager.getInstance();
        qm.Save(EntityEnum.CREDITBILLS, creditbills);
        System.out.println("Credit bills saved.");
        return true;
    }
    
    public static boolean newAskadvance(Integer transactionid, Integer roomno, String guestname, Date checkindate, Integer advancepaid, Integer roomrent, Integer difference)
    {
        Askadvance askadvance = new Askadvance(Integer.SIZE, transactionid, roomno, guestname, checkindate, advancepaid, roomrent, difference);
        
        QueryManager qm =  QueryManager.getInstance();
        qm.Save(EntityEnum.ASKADVANCE, askadvance);
        System.out.println("Askadvance saved.");
        return true;
    }
    
    public static boolean newLaundryItem(String itemname, String itemdesc, double itemrate, Integer totalqty)
    {
        Items items = new Items(new Integer(0), itemname, itemdesc, itemrate, totalqty);
        
        QueryManager qm =  QueryManager.getInstance();
        
        qm.Save(EntityEnum.ITEMS, items);
        System.out.println("Items saved.");
        return true;
    }
    
    public static boolean newMasterLaundryTransaction(String recvfrom, String recvto, Date todaydate, Integer totalbill, Users currentUser)
    {
        Itemmastertrans itemmastertrans = new Itemmastertrans(new Integer(0), recvfrom, recvto, todaydate, totalbill);
        itemmastertrans.setUserId(currentUser);
        QueryManager qm =  QueryManager.getInstance();
        
        qm.Save(EntityEnum.ITEMMASTERTRANS, itemmastertrans);
        System.out.println("Itemmastertrans saved.");
        return true;
    }
    
    public static boolean newItemTransaction(Integer receivedCount, Integer sentCount, double subtotal,Itemmastertrans mastertrans, Items selectitems, Integer balance)
    {
        Itemtrans itemtrans = new Itemtrans(new Integer(0), receivedCount, sentCount, subtotal, balance);
        System.out.println("here too: " + mastertrans.getOid());
        itemtrans.setItemMasterTransid(mastertrans);
        itemtrans.setItemid(selectitems);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ITEMTRANS, itemtrans);
        System.out.println("Itemtrans saved.");
        return true;
    }
    
    public static boolean newRoomAmenity(Integer singlebedsheetqty, Integer singlebedsheetqtytemp, Integer doublebedsheetqty, Integer doublebedsheetqtytemp, Integer pillowqty, Integer pillowqtytemp, Integer blanketqty, Integer blanketqtytemp, Integer towelqty, Integer towelqtytemp, Room thisroom, Items thissingleb, Items thisdoubleb, Items pillow, Items blanket, Items towel)
    {
        Amenityroom amenityroom = new Amenityroom(new Integer(0),singlebedsheetqty, singlebedsheetqtytemp, doublebedsheetqty, doublebedsheetqtytemp, pillowqty, pillowqtytemp, blanketqty, blanketqtytemp, towelqty, towelqtytemp);
        amenityroom.setRoomno(thisroom);
        amenityroom.setSinglebedsheet(thissingleb);
        amenityroom.setDoublebedsheet(thisdoubleb);
        amenityroom.setPillow(pillow);
        amenityroom.setBlanket(blanket);
        amenityroom.setTowel(towel);
        
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.AMENITYROOM, amenityroom);
        System.out.println("Amenity Room saved.");
        return true;
        
    }
    
    public static boolean newItemInStore(Integer qty, Items thisitem)
    {
        Iteminstore iteminstore = new Iteminstore(new Integer(0), qty);
        iteminstore.setItemid(thisitem);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ITEMINSTORE, iteminstore);
        System.out.println("Item in store saved.");
        return true;
    }
    
    public static boolean newItemInLaundry(Integer qty, Items thisitem)
    {
        Iteminlaundry iteminlaundry = new Iteminlaundry(new Integer(0), qty);
        iteminlaundry.setItemid(thisitem);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ITEMINLAUNDRY, iteminlaundry);
        System.out.println("Item in laundry saved.");
        return true;
    }
    
    public static boolean newItemInUse(Integer qty, Items thisitem)
    {
        Iteminuse iteminuse = new Iteminuse(new Integer(0), qty);
        iteminuse.setItemid(thisitem);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ITEMINUSE, iteminuse);
        System.out.println("Item in use saved.");
        return true;
    }
    
    public static boolean newItemDirty(Integer qty, Items thisitem)
    {
        Itemdirty itemdirty = new Itemdirty(new Integer(0), qty);
        itemdirty.setItemid(thisitem);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.ITEMDIRTY, itemdirty);
        System.out.println("Item dirty saved.");
        return true;
    }
    
    public static boolean newDaysession(Date startdate, Boolean status)
    {
        Daysession daysession = new Daysession(new Integer(0), startdate, status);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.DAYSESSION, daysession);
        System.out.println("Day session saved.");
        return true;
    }
    
    public static boolean newDayTariff(Date startdatetime, Date enddatetime, BigDecimal roomrent, Integer extrabed, BigDecimal taxapplicable, BigDecimal daytotal, Bills thisbill)
    {
        Daytariff daytariff = new Daytariff(new Integer(0), startdatetime, enddatetime, roomrent, extrabed, taxapplicable, daytotal);
        daytariff.setBillno(thisbill);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.DAYTARIFF, daytariff);
        return true;
    }
    
    public static boolean newTotalTariff(Integer grandroomtotal, Integer grandextratotal, Integer grandtaxtotal, Integer totalbill, Bills billno)
    {
        Totaltariff totaltariff = new Totaltariff(new Integer(0), grandroomtotal, grandextratotal, grandtaxtotal, totalbill);
        totaltariff.setBillno(billno);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.TOTALTARIFF, totaltariff);
        return true;
    }
    
    public static boolean newBookedRoomAmenities(Date senddate, Integer singlebedsheet, Integer doublebedsheet, Integer pillow, Integer towel, Integer blanket, Date receiveddate, Integer singlebedsheetrecv, Integer doublebedsheetrecv, Integer pillowrecv, Integer towelrecv, Integer blanketrecv, Boolean status, Roomcleaners sentby, Roomcleaners recvby, Room thisroom, Bookings thisbookings)
    {
        Bookedroomamenities bookedroomamenities = new Bookedroomamenities(new Integer(0), senddate, singlebedsheet, doublebedsheet, pillow, towel, blanket, receiveddate, singlebedsheetrecv, doublebedsheetrecv, pillowrecv, towelrecv, blanketrecv, status);
        
        bookedroomamenities.setSentby(sentby);
        bookedroomamenities.setReceivedby(recvby);
        bookedroomamenities.setRoomno(thisroom);
        bookedroomamenities.setBookingid(thisbookings);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.BOOKEDROOMAMENITIES, bookedroomamenities);
        return true;
    }
    
    public static boolean newReservationWithoutAdvance(String bookedby, Integer noofrooms, Date arrivaldatetime, Integer noofpeople, String city, Date currentdatetime, BigInteger contactno, Users thisusers, String note, Roomtype thisroomtype)
    {
        Reservationwoadvance reservationwoadvance = new Reservationwoadvance(new Integer(0), bookedby, noofrooms, arrivaldatetime, noofpeople, city, currentdatetime);
        reservationwoadvance.setContactno(contactno);
        reservationwoadvance.setUserid(thisusers);
        reservationwoadvance.setNote(note);
        reservationwoadvance.setRoomtypeid(thisroomtype);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.RESERVATIONWOADVANCE, reservationwoadvance);
        return true;
        
    }
    
    public static boolean newReservationWithAdvance(String guestname, Integer noofrooms, Date arrivaldatetime, Integer noofpeople, Long contactno, String city, Date currentdate, Integer advancepaid, Users thisusers, String note, Roomtype thisroomtype, Receipts thisreceipts)
    {
        Reservationwadvance reservationwadvance = new Reservationwadvance(new Integer(0), guestname, noofrooms, arrivaldatetime, noofpeople, contactno, city, currentdate , advancepaid);
        reservationwadvance.setContactno(contactno);
        reservationwadvance.setUserid(thisusers);
        reservationwadvance.setNote(note);
        reservationwadvance.setRoomtypeid(thisroomtype);
        reservationwadvance.setReceipts(thisreceipts);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.RESERVATIONWADVANCE, reservationwadvance);
        return true;
    }
    public static boolean newCreditBillTrans(Integer amountrecvd, Date currentDate, Creditbills creditbills, Bills bills)
    {
        Creditbilltrans creditbilltrans = new Creditbilltrans(new Integer(0), amountrecvd, currentDate);
        creditbilltrans.setCreditbillid(creditbills);
        creditbilltrans.setBillid(bills);
        
        QueryManager qm = QueryManager.getInstance();
        qm.Save(EntityEnum.CREDITBILLRECV, creditbilltrans);
        return true;
    }
    
    
}
