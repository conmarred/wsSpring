package converters;

import dto.AlumnoDto;
import dto.CursoDto;
import dto.MatriculaDto;
import model.Alumno;
import model.Curso;
import model.Matricula;

public interface ConversorEntityDto {
	//conversor para curso
	public CursoDto cursoToDto(Curso curso);
	public Curso dtoToCurso(CursoDto dto);
	//conversor para alumno
	public AlumnoDto alumnoToDto(Alumno alumno);
	public Alumno dtoToAlumno(AlumnoDto dto);
	//conversor para matricula
	public MatriculaDto matriculaToDto(Matricula matricula);
	public Matricula dtoToMatricula(MatriculaDto dto);
}
