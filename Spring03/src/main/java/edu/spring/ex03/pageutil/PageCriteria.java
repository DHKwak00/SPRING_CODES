package edu.spring.ex03.pageutil;

// ���������� ������ ������ ��ȣ��
// �� ���������� ������ �Խñ��� ������ �����ϴ� Ŭ����
// -> paging ó���� �ʿ��� start�� end ��ȣ�� �� �� ����
public class PageCriteria {
	private int page; // ���� ������ ��ȣ
	private int numsPerPage; // �� �������� �Խñ� ����
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 3;
	}
	
	public PageCriteria(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	// getter/setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	// ���� �������� �������� ���� �� �Ϸù�ȣ(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// ���� �������� �������� ������ �� �Ϸù�ȣ(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
	
	
}



