import GradeLabel from "../components/GradeLabel";

export default function Home() {
  return (
    // <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
    //   <h2 className="text-3xl font-bold mb-4">Welcome to Kilovia</h2>
    //   <p className="text-lg text-gray-700">This is the Home page.</p>
    //   <div className="grid grid-cols-1 gap-4 w-full max-w-4xl md:grid-cols-3">
    //       <div className="bg-red-400 text-white p-6 rounded-xl shadow">Box 1</div>
    //       <div className="bg-green-400 text-white p-6 rounded-xl shadow">Box 2</div>
    //       <div className="bg-blue-400 text-white p-6 rounded-xl shadow">Box 3</div>
    //   </div>
    // </div>

    <div className="bg-white overflow-x-hidden w-full relative">
      <GradeLabel 
        mainColor="#4CAF50" 
        triangleColor="#1B5E20" 
        text="LỚP 1" 
      />
    </div>
  );
}
