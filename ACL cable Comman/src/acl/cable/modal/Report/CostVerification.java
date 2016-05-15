/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Cost;
import acl.cable.modal.comman.Machine;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.Worker;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Eminda
 */
public class CostVerification implements Report {

    private double cost;
    private Date startDate;
    private Date endDate;
    /**
     * Methods below are used to store search results. They are used on request.
     * Some may not be needed each and every time;
     */
    private Engineer engineer;
    private Worker worker;
    private HashMap<Engineer, ArrayList<Cost>> engineerVsCost = new HashMap();
    private HashMap<Machine, ArrayList<Cost>> machineVsCost = new HashMap<>();
    private HashMap<Worker, ArrayList<Cost>> workerVsCost = new HashMap<>();
    private ArrayList<Cost> costs = new ArrayList<>();

    public CostVerification() {
    }

    public HashMap<Engineer, ArrayList<Cost>> getEngineerVsCostVerification() {
        throw new RuntimeException();
    }

    public HashMap<Machine, ArrayList<Cost>> getMachineVsCostVerification() {
        throw new RuntimeException();
    }

    public HashMap<Worker, ArrayList<Cost>> getWorkerVsCostVerification() {
        throw new RuntimeException();
    }

    public ArrayList<Cost> getCostsMadeByAnEngineer(Engineer engineer) {
        throw new RuntimeException();
    }

    public ArrayList<Cost> getCostsResultedWorker(Worker worker) {
        throw new RuntimeException();
    }

    public ArrayList<Cost> getCostsByMachine(Machine machine) {
        throw new RuntimeException();
    }
}
