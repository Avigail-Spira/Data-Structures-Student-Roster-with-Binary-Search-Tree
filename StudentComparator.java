import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

  //compares students alphabetically
    @Override
	public int compare(Student student1, Student student2)
	{
		int y = student1.last.compareTo(student2.last);
		if (y != 0) return y;
		y = student1.first.compareTo(student2.first);
		if (y != 0) return y;
		return student1.IDNo.compareTo(student2.IDNo);
		
	}
}