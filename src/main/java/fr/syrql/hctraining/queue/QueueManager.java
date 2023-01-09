package fr.syrql.hctraining.queue;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.queue.io.IQueue;
import fr.syrql.hctraining.queue.type.unranked.HCFQueueUnranked;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueueManager {

    private final HCTraining hcTraining;
    private final List<IQueue> queues;

    public QueueManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.queues = new ArrayList<>();
        this.setupQueues();
    }

    private void setupQueues() {
        this.queues.add(new HCFQueueUnranked());
    }

    public List<IQueue> getQueues() {
        return queues;
    }
}
