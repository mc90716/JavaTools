package com.tomcat.pipeline.pipeline;

import com.tomcat.pipeline.valve.Valve;

public class StandardPipeline implements Pipeline {

	protected Valve first = null;
	protected Valve basic = null;

	@Override
	public Valve getFirst() {
		return first;
	}

	@Override
	public void addNext(Valve next) {
		if (first == null) {
			first = next;
		} else {
			Valve current = first;
			while (current != null) {
				if (current.getNext() == null) {
					current.setNext(next);
					break;
				}
				current = current.getNext();
			}
		}
	}

	public void run() {
		String line = "1234567890";
		getFirst().invoke(line);
	}

	@Override
	public void setBasic(Valve valve) {
		Valve oldBasic = this.basic;
		if (oldBasic == valve) {
			return;
		}
		if (valve == null) {
			return;
		}
		Valve current = first;
		while (current != null) {
			if (current.getNext() == oldBasic) {
				current.setNext(valve);
				break;
			}
			current = current.getNext();
		}
		this.basic = valve;
	}
}
