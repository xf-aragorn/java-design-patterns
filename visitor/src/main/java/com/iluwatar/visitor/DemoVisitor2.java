package com.iluwatar.visitor;

import java.util.ArrayList;
import java.util.List;


//单个单子的接口（相当于Element）
 interface Bill {
    void accept(Viewer viewer);
}


//抽象单子类，一个高层次的单子抽象
 abstract class AbstractBill implements Bill{
    protected double amount;
    protected String item;

    public AbstractBill(double amount, String item) {
        super();
        this.amount = amount;
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

     String getItem() {
        return item;
    }
}


//收入单子
 class IncomeBill extends AbstractBill{

    public IncomeBill(double amount, String item) {
        super(amount, item);
    }

    public void accept(Viewer viewer) {
        if (viewer instanceof AbstractViewer) {
            ((AbstractViewer)viewer).viewIncomeBill(this);
            return;
        }
        viewer.viewAbstractBill(this);
    }
}


//消费的单子
 class ConsumeBill extends AbstractBill{

    public ConsumeBill(double amount, String item) {
        super(amount, item);
    }

    public void accept(Viewer viewer) {
        if (viewer instanceof AbstractViewer) {
            ((AbstractViewer)viewer).viewConsumeBill(this);
            return;
        }
        viewer.viewAbstractBill(this);
    }
}


//超级访问者接口（它支持定义高层操作）
 interface Viewer{
    void viewAbstractBill(AbstractBill bill);
}


//比Viewer接口低一个层次的访问者接口
 abstract class AbstractViewer implements Viewer{

    //查看消费的单子
    abstract void viewConsumeBill(ConsumeBill bill);

    //查看收入的单子
    abstract void viewIncomeBill(IncomeBill bill);

    public final void viewAbstractBill(AbstractBill bill){}
}


//老板类，查看账本的类之一，作用于最低层次结构
 class Boss extends AbstractViewer{

    private double totalIncome;

    private double totalConsume;

    //老板只关注一共花了多少钱以及一共收入多少钱，其余并不关心
    public void viewConsumeBill(ConsumeBill bill) {
        totalConsume += bill.getAmount();
    }

    public void viewIncomeBill(IncomeBill bill) {
        totalIncome += bill.getAmount();
    }

    public double getTotalIncome() {
        System.out.println("老板查看一共收入多少，数目是：" + totalIncome);
        return totalIncome;
    }

    public double getTotalConsume() {
        System.out.println("老板查看一共花费多少，数目是：" + totalConsume);
        return totalConsume;
    }
}


//注册会计师类，查看账本的类之一，作用于最低层次结构
 class CPA extends AbstractViewer{

    //注会在看账本时，如果是支出，则如果支出是工资，则需要看应该交的税交了没
    public void viewConsumeBill(ConsumeBill bill) {
        if (bill.getItem().equals("工资")) {
            System.out.println("注会查看账本时，如果单子的消费目的是发工资，则注会会查看有没有交个人所得税。");
        }
    }

    //如果是收入，则所有的收入都要交税
    public void viewIncomeBill(IncomeBill bill) {
        System.out.println("注会查看账本时，只要是收入，注会都要查看公司交税了没。");
    }
}


//财务主管类，查看账本的类之一，作用于高层的层次结构
 class CFO implements Viewer {

    //财务主管对每一个单子都要核对项目和金额
    public void viewAbstractBill(AbstractBill bill) {
        System.out.println("财务主管查看账本时，每一个都核对项目和金额，金额是" + bill.getAmount() + "，项目是" + bill.getItem());
    }

}


//账本类（相当于ObjectStruture）
 class AccountBook {
    //单子列表
    private List<Bill> billList = new ArrayList<Bill>();

    //添加单子
    public void addBill(Bill bill){
        billList.add(bill);
    }

    //供账本的查看者查看账本
    public void show(Viewer viewer){
        for (Bill bill : billList) {
            bill.accept(viewer);
        }
    }
}


public class DemoVisitor2 {

    public static void main(String[] args) {
        AccountBook accountBook = new AccountBook();
        //添加两条收入
        accountBook.addBill(new IncomeBill(10000, "卖商品"));
        accountBook.addBill(new IncomeBill(12000, "卖广告位"));
        //添加两条支出
        accountBook.addBill(new ConsumeBill(1000, "工资"));
        accountBook.addBill(new ConsumeBill(2000, "材料费"));

        Viewer boss = new Boss();
        Viewer cpa = new CPA();
        Viewer cfo = new CFO();

        //两个访问者分别访问账本
        accountBook.show(cpa);
        accountBook.show(boss);
        accountBook.show(cfo);

        ((Boss) boss).getTotalConsume();
        ((Boss) boss).getTotalIncome();
    }
}
